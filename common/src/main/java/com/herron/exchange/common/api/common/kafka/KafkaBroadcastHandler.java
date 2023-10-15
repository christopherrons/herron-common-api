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

    public KafkaBroadcastHandler(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public boolean broadcastMessage(PartitionKey partitionKey, Message message) {
        return keyToProducer.computeIfAbsent(partitionKey, k -> {
                    var producer = new KafkaBroadcastProducer(partitionKey, kafkaTemplate, new EventLogger());
                    producer.startBroadcasting();
                    return producer;
                }
        ).broadcastMessage(message);
    }

    public void endBroadCast(PartitionKey partitionKey) {
        keyToProducer.get(partitionKey).endBroadcasting();
    }

}
