package com.herron.exchange.common.api.common.kafka;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.logging.EventLogger;
import com.herron.exchange.common.api.common.messages.common.PartitionKey;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KafkaBroadcastHandler {

    private final Map<PartitionKey, KafkaBroadcastProducer> keyToProducer = new ConcurrentHashMap<>();
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final Map<PartitionKey, Integer> keyToMessageUpdateInterval;

    public KafkaBroadcastHandler(KafkaTemplate<String, Object> kafkaTemplate) {
        this(kafkaTemplate, Map.of());
    }

    public KafkaBroadcastHandler(KafkaTemplate<String, Object> kafkaTemplate, Map<PartitionKey, Integer> keyToMessageUpdateInterval) {
        this.kafkaTemplate = kafkaTemplate;
        this.keyToMessageUpdateInterval = keyToMessageUpdateInterval;
    }

    public boolean broadcastMessage(PartitionKey partitionKey, Message message) {
        return keyToProducer.computeIfAbsent(partitionKey, k -> {
                    var producer = new KafkaBroadcastProducer(partitionKey, kafkaTemplate, new EventLogger(partitionKey.toString(), keyToMessageUpdateInterval.getOrDefault(partitionKey, 1000)));
                    producer.startBroadcasting();
                    return producer;
                }
        ).broadcastMessage(message);
    }

    public void endBroadCast(PartitionKey partitionKey) {
        keyToProducer.get(partitionKey).endBroadcasting();
    }

}
