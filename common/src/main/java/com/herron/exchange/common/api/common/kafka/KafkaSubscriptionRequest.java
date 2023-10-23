package com.herron.exchange.common.api.common.kafka;

import com.herron.exchange.common.api.common.api.kafka.KafkaMessageHandler;
import com.herron.exchange.common.api.common.messages.common.PartitionKey;

public record KafkaSubscriptionRequest(String groupId, PartitionKey partitionKey, KafkaMessageHandler messageHandler, Integer offset, int eventLoggingRate) {
}
