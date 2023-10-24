package com.herron.exchange.common.api.common.kafka;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.kafka.model.KafkaSubscriptionRequest;
import com.herron.exchange.common.api.common.messages.common.PartitionKey;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.ConsumerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KafkaConsumerClient {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MessageFactory messageFactory;
    private final ConsumerFactory<String, String> consumerFactory;
    private final Map<PartitionKey, KafkaBroadcastSubscription> keyToSubscription = new ConcurrentHashMap<>();

    public KafkaConsumerClient(MessageFactory messageFactory, ConsumerFactory<String, String> consumerFactory) {
        this.messageFactory = messageFactory;
        this.consumerFactory = consumerFactory;
    }

    public synchronized KafkaBroadcastSubscription subscribeToBroadcastTopic(KafkaSubscriptionRequest request) {
        var partitionKey = request.details().partitionKey();
        return keyToSubscription.computeIfAbsent(partitionKey, k -> {

            TopicPartition topicPartition = new TopicPartition(partitionKey.topicEnum().getTopicName(), partitionKey.partitionId());
            try (var consumer = consumerFactory.createConsumer(request.details().groupId())) {
                consumer.assign(List.of(topicPartition));

                if (request.details().offset() != null) {
                    consumer.seek(topicPartition, request.details().offset());
                }

                var subscription = new KafkaBroadcastSubscription(messageFactory, consumer, request);
                subscription.run();
                return subscription;

            } catch (Exception e) {
                logger.error("Error while creating consumer.", e);
            }

            return null;
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
