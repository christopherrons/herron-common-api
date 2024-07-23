package com.herron.exchange.common.api.common.kafka;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.kafka.model.KafkaSubscriptionRequest;
import com.herron.exchange.common.api.common.messages.common.PartitionKey;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.ConsumerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KafkaConsumerClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerClient.class);
    private final MessageFactory messageFactory;
    private final ConsumerFactory<String, String> consumerFactory;
    private final Map<PartitionKey, KafkaBroadcastSubscription> keyToSubscription = new ConcurrentHashMap<>();

    public KafkaConsumerClient(MessageFactory messageFactory, ConsumerFactory<String, String> consumerFactory) {
        this.messageFactory = messageFactory;
        this.consumerFactory = consumerFactory;
    }

    public synchronized KafkaBroadcastSubscription subscribeToBroadcastTopic(KafkaSubscriptionRequest request) {
        return keyToSubscription.computeIfAbsent(request.details().partitionKey(), pk -> {
            TopicPartition topicPartition = new TopicPartition(pk.topicEnum().getTopicName(), pk.partitionId());
            var subscription = new KafkaBroadcastSubscription(messageFactory, request);
            subscription.run(consumerFactory, topicPartition);
            return subscription;
        });
    }

    public void destroy() {
        keyToSubscription.values().forEach(KafkaTopicSubscription::stop);
    }

    public void stop(PartitionKey partitionKey) {
        if (keyToSubscription.containsKey(partitionKey)) {
            keyToSubscription.get(partitionKey).stop();
        }
    }
}
