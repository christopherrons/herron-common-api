package com.herron.exchange.common.api.common.model;

import com.herron.exchange.common.api.common.enums.KafkaTopicEnum;

public record PartitionKey(KafkaTopicEnum topicEnum, int partitionId) {

    public String description() {
        return topicEnum.getTopicName() + "-" + partitionId;
    }
}
