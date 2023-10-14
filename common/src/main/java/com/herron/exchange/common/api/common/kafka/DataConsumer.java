package com.herron.exchange.common.api.common.kafka;

import com.herron.exchange.common.api.common.api.broadcasts.BroadcastMessage;
import com.herron.exchange.common.api.common.model.PartitionKey;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static com.herron.exchange.common.api.common.enums.MessageTypesEnum.deserializeMessage;

public abstract class DataConsumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Map<PartitionKey, AtomicLong> partitionToSequenceNumberHandler = new ConcurrentHashMap<>();

    public BroadcastMessage deserializeBroadcast(ConsumerRecord<String, String> consumerRecord, PartitionKey partitionKey) {
        BroadcastMessage broadcastMessage = (BroadcastMessage) deserializeMessage(consumerRecord.key(), consumerRecord.value());
        if (broadcastMessage == null || broadcastMessage.serialize().isEmpty()) {
            logger.error("Unable to map message: {}", consumerRecord);
            return null;
        }

        long expected = getSequenceNumber(partitionKey);
        if (broadcastMessage.sequenceNumber() != expected) {
            logger.warn("GAP detected: Expected={}, Incoming={}", expected, broadcastMessage.sequenceNumber());
        }

        return broadcastMessage;
    }

    private long getSequenceNumber(PartitionKey partitionKey) {
        return partitionToSequenceNumberHandler.computeIfAbsent(partitionKey, k -> new AtomicLong(1)).getAndIncrement();
    }
}
