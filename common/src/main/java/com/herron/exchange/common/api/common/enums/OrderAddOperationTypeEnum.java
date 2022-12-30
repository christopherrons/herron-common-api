package com.herron.exchange.common.api.common.enums;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum OrderAddOperationTypeEnum {
    INVALID_ADD_TYPE(null),
    NEW_ORDER("new-order");

    private static final Map<String, OrderAddOperationTypeEnum> VALUES_BY_IDENTIFIER = stream(OrderAddOperationTypeEnum.values()).collect(toMap(OrderAddOperationTypeEnum::getValue, identity()));
    private final String value;

    OrderAddOperationTypeEnum(String value) {
        this.value = value;
    }

    public static OrderAddOperationTypeEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value, INVALID_ADD_TYPE);
    }

    public String getValue() {
        return value;
    }
}
