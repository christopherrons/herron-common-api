package com.herron.exchange.common.api.common.enums;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum KafkaTopicEnum {
    TOP_OF_BOOK_QUOTE("top-of-book-quote", "The best bid and ask quote currently in the orderbook."),
    USER_ORDER_DATA("user-order-data", "Orders enterd by members of the exchange"),
    SYSTEM_ORDER_DATA("system-order-data", "Orders entering and generated by the trading engine."),
    PREVIOUS_SETTLEMENT_PRICE_DATA("previous-settlement-price-data", "Previous day settlement prices."),
    REFERENCE_DATA("reference-data", "Reference start if day."),
    TRADE_DATA("trade-data", "Trades generated by the trading engine."),
    REAL_TIME_PRICES("real-time-prices", "Real time prices."),
    AUDIT_TRAIL("audit-trail", "Reference data, state changes, orders and trades entering and generated by the trading engine.");

    private final String topicName;
    private final String description;
    private static final Map<String, KafkaTopicEnum> VALUES_BY_IDENTIFIER = stream(KafkaTopicEnum.values()).collect(toMap(KafkaTopicEnum::getTopicName, identity()));

    KafkaTopicEnum(String topicName, String description) {
        this.topicName = topicName;
        this.description = description;
    }

    public String getTopicName() {
        return topicName;
    }

    public static KafkaTopicEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value.toLowerCase(), null);
    }

}
