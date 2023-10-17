package com.herron.exchange.common.api.common.enums;

public enum KafkaTopicEnum {
    ORDER_DATA("order-data"),
    PREVIOUS_SETTLEMENT_PRICE_DATA("previous-settlement-price-data"),
    REFERENCE_DATA("reference-data"),
    TRADE_DATA("trade-data"),
    AUDIT_TRAIL("audit-trail");

    private final String topicName;

    KafkaTopicEnum(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }
}
