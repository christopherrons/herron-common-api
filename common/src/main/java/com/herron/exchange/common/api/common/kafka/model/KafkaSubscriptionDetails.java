package com.herron.exchange.common.api.common.kafka.model;

import com.herron.exchange.common.api.common.messages.common.PartitionKey;

public record KafkaSubscriptionDetails(String groupId, PartitionKey partitionKey, Integer offset, int eventLoggingRate) {
}
