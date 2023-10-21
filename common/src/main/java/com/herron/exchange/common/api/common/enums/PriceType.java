package com.herron.exchange.common.api.common.enums;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum PriceType {
    SETTLEMENT("settlement"),
    LAST_PRICE("last price"),
    BID_PRICE( "bid price"),
    ASK_PRICE( "ask price"),
    MID_BID_ASK_PRICE("mid bid ask price"),
    VWAP("volume weighted average price"),
    HISTORICAL("historical"),
    THEORETICAL("theoretical");

    private static final Map<String, PriceType> VALUES_BY_IDENTIFIER = stream(PriceType.values()).collect(toMap(PriceType::getValue, identity()));
    private final String value;

    PriceType(String value) {
        this.value = value;
    }

    public static PriceType fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value, null);
    }

    public String getValue() {
        return value;
    }
}
