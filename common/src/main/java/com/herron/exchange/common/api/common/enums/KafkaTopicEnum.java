package com.herron.exchange.common.api.common.enums;

public enum KafkaTopicEnum {
    BITSTAMP_MARKET_DATA("bitstamp-market-data"),
    HERRON_REFERENCE_DATA("herron-reference-data"),
    HERRON_AUDIT_TRAIL("herron-audit-trail");

    private final String topicName;

    KafkaTopicEnum(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }
}
