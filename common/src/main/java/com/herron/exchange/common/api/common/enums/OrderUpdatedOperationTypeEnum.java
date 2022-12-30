package com.herron.exchange.common.api.common.enums;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum OrderUpdatedOperationTypeEnum {
    INVALID_UPDATE_TYPE(null),
    PARTIAL_FILL("partial-fill"),
    EXTERNAL_UPDATE("external-update");

    private static final Map<String, OrderUpdatedOperationTypeEnum> VALUES_BY_IDENTIFIER = stream(OrderUpdatedOperationTypeEnum.values()).collect(toMap(OrderUpdatedOperationTypeEnum::getValue, identity()));
    private final String value;

    OrderUpdatedOperationTypeEnum(String value) {
        this.value = value;
    }

    public static OrderUpdatedOperationTypeEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value, INVALID_UPDATE_TYPE);
    }

    public String getValue() {
        return value;
    }
}