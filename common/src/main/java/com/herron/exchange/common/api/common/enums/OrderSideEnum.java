package com.herron.exchange.common.api.common.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum OrderSideEnum {
    BID(0),
    ASK(1);

    private static final Map<Integer, OrderSideEnum> VALUES_BY_IDENTIFIER = stream(OrderSideEnum.values()).collect(toMap(OrderSideEnum::getValue, identity()));
    private final int value;

    OrderSideEnum(int value) {
        this.value = value;
    }

    public static OrderSideEnum fromValue(int value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value, null);
    }

    public int getValue() {
        return value;
    }
}
