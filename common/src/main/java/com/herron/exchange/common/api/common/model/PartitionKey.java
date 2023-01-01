package com.herron.exchange.common.api.common.model;

import com.herron.exchange.common.api.common.enums.TopicEnum;

public record PartitionKey(TopicEnum topicEnum, int partitionId) {

    public String description() {
        return topicEnum.getTopicName() + "-" + partitionId;
    }
}
