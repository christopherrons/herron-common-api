package com.herron.exchange.common.api.common.kafka;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.messages.common.PartitionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KafkaBroadcastHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaBroadcastHandler.class);
    private final Map<PartitionKey, KafkaBroadcastProducer> keyToProducer = new ConcurrentHashMap<>();
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaBroadcastHandler(KafkaTemplate<String, Object> kafkaTemplate) {
        this(kafkaTemplate, Map.of());
    }

    public KafkaBroadcastHandler(KafkaTemplate<String, Object> kafkaTemplate, Map<PartitionKey, KafkaBroadcastProducer> initialKeyToProducer) {
        this.kafkaTemplate = kafkaTemplate;
        initialKeyToProducer.values().forEach(KafkaBroadcastProducer::startBroadcasting);
        keyToProducer.putAll(initialKeyToProducer);
    }

    public boolean broadcastMessage(PartitionKey partitionKey, Message message) {
        return keyToProducer.computeIfAbsent(partitionKey, k -> {
                    var producer = new KafkaBroadcastProducer(partitionKey, kafkaTemplate);
                    producer.startBroadcasting();
                    return producer;
                }
        ).broadcastMessage(message);
    }

    public void endBroadCast(PartitionKey partitionKey) {
        keyToProducer.get(partitionKey).endBroadcasting();
    }

}
