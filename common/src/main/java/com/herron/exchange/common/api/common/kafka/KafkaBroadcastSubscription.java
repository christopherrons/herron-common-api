package com.herron.exchange.common.api.common.kafka;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.api.kafka.KafkaMessageHandler;
import com.herron.exchange.common.api.common.logging.EventLogger;
import com.herron.exchange.common.api.common.messages.BroadcastMessage;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.concurrent.atomic.AtomicLong;

public class KafkaBroadcastSubscription extends KafkaTopicSubscription {
    private final AtomicLong sequenceNumber = new AtomicLong();
    private final MessageFactory messageFactory;
    private final KafkaMessageHandler messageHandler;

    protected KafkaBroadcastSubscription(MessageFactory messageFactory,
                                         Consumer<String, String> consumer,
                                         KafkaSubscriptionRequest request) {
        super(consumer, new EventLogger(request.partitionKey().toString(), request.eventLoggingRate()));
        this.messageFactory = messageFactory;
        this.messageHandler = request.messageHandler();
    }

    @Override
    protected void handleEvent(ConsumerRecord<String, String> consumerRecord) {
        long expected = sequenceNumber.getAndIncrement();

        BroadcastMessage broadcastMessage = messageFactory.deserializeMessage(consumerRecord.value(), BroadcastMessage.class);
        if (broadcastMessage == null || messageFactory.serialize(broadcastMessage).isEmpty()) {
            logger.error("Unable to map message: {}", consumerRecord);
        }

        if (broadcastMessage != null && broadcastMessage.sequenceNumber() != expected) {
            logger.warn("GAP detected: Expected={}, Incoming={}", expected, broadcastMessage.sequenceNumber());
        }

        messageHandler.onMessage(broadcastMessage);
    }
}
