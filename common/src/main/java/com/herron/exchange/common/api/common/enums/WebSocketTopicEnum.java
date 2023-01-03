package com.herron.exchange.common.api.common.enums;

public enum WebSocketTopicEnum {
    ORDER_STREAM("order-stream"),
    TRADE_STREAM("trade-stream");

    private final String topicName;

    WebSocketTopicEnum(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }
}
