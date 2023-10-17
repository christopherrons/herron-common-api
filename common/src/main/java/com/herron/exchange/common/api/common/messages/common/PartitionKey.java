package com.herron.exchange.common.api.common.messages.common;

import com.herron.exchange.common.api.common.enums.KafkaTopicEnum;

public record PartitionKey(KafkaTopicEnum topicEnum, int partitionId) {
}
