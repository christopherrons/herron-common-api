package com.herron.exchange.common.api.common.messages;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.logging.EventLogger;
import com.herron.exchange.common.api.common.model.PartitionKey;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

public abstract class KafkaBroadCastProducer {

    private final PartitionKey partitionKey;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final AtomicLong sequenceNumberHandler = new AtomicLong(1);
    private final EventLogger eventLogger;

    public KafkaBroadCastProducer(PartitionKey partitionKey, KafkaTemplate<String, Object> kafkaTemplate, EventLogger eventLogger) {
        this.partitionKey = partitionKey;
        this.kafkaTemplate = kafkaTemplate;
        this.eventLogger = eventLogger;
    }

    public synchronized void broadcastMessage(Message message) {
        if (message == null) {
            return;
        }
        eventLogger.logEvent();
        var broadCast = new HerronBroadcastMessage(message, message.messageType().getMessageTypeId(), sequenceNumberHandler.getAndIncrement(), Instant.now().toEpochMilli());
        kafkaTemplate.send(partitionKey.topicEnum().getTopicName(), partitionKey.partitionId(), broadCast.messageType().getMessageTypeId(), broadCast);
    }
}
