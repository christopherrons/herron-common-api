package com.herron.exchange.common.api.common.kafka;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.logging.EventLogger;
import com.herron.exchange.common.api.common.messages.BroadcastMessage;
import com.herron.exchange.common.api.common.messages.common.PartitionKey;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class KafkaDataConsumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Map<PartitionKey, AtomicLong> partitionToSequenceNumberHandler = new ConcurrentHashMap<>();
    private final Map<PartitionKey, EventLogger> partitionToEventLogger = new ConcurrentHashMap<>();
    private final MessageFactory messageFactory;
    private final Map<PartitionKey, Integer> keyToMessageUpdateInterval;

    protected KafkaDataConsumer(MessageFactory messageFactory) {
        this(messageFactory, Map.of());
    }

    protected KafkaDataConsumer(MessageFactory messageFactory, Map<PartitionKey, Integer> keyToMessageUpdateInterval) {
        this.messageFactory = messageFactory;
        this.keyToMessageUpdateInterval = keyToMessageUpdateInterval;
    }

    public BroadcastMessage deserializeBroadcast(ConsumerRecord<String, String> consumerRecord, PartitionKey partitionKey) {
        long expected = getSequenceNumber(partitionKey);
        logEvent(partitionKey);

        BroadcastMessage broadcastMessage = messageFactory.deserializeMessage(consumerRecord.value(), BroadcastMessage.class);
        if (broadcastMessage == null || messageFactory.serialize(broadcastMessage).isEmpty()) {
            logger.error("Unable to map message: {}", consumerRecord);
            return null;
        }

        if (broadcastMessage.sequenceNumber() != expected) {
            logger.warn("GAP detected: Expected={}, Incoming={}", expected, broadcastMessage.sequenceNumber());
        }

        return broadcastMessage;
    }

    private long getSequenceNumber(PartitionKey partitionKey) {
        return partitionToSequenceNumberHandler.computeIfAbsent(partitionKey, k -> new AtomicLong(1)).getAndIncrement();
    }

    private void logEvent(PartitionKey partitionKey) {
        partitionToEventLogger.computeIfAbsent(partitionKey, k -> new EventLogger(partitionKey.toString(), keyToMessageUpdateInterval.getOrDefault(partitionKey, 1000))).logEvent();
    }

    public double getTotalNumberOfEvents() {
        return partitionToEventLogger.values().stream().mapToDouble(EventLogger::totalNrOfEvents).sum();
    }
}
