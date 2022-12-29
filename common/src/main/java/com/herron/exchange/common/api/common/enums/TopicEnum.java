package com.herron.exchange.common.api.common.enums;

public enum TopicEnum {

    BITSTAMP_AUDIT_TRAIL("bitstamp-audit-trail");

    private final String topicName;

    TopicEnum(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }
}
