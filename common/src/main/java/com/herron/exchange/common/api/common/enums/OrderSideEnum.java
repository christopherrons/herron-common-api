package com.herron.exchange.common.api.common.enums;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum OrderSideEnum {
    INVALID_ORDER_SIDE(-1),
    BUY(0),
    ASK(1);

    private static final Map<Integer, OrderSideEnum> VALUES_BY_IDENTIFIER = stream(OrderSideEnum.values()).collect(toMap(OrderSideEnum::getValue, identity()));
    private final int value;

    OrderSideEnum(int value) {
        this.value = value;
    }

    public static OrderSideEnum fromValue(int value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value, INVALID_ORDER_SIDE);
    }

    public int getValue() {
        return value;
    }
}
