package com.herron.exchange.common.api.common.enums;

public enum TopicEnum {
    BITSTAMP_MARKET_DATA("bitstamp-market-data"),
    HERRON_AUDIT_TRAIL("herron-audit-trail");

    private final String topicName;

    TopicEnum(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }
}
