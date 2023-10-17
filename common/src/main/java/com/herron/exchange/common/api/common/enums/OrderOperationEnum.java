package com.herron.exchange.common.api.common.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum OrderOperationEnum {
    INVALID_ORDER_OPERATION("invalid"),
    INSERT("insert"),
    UPDATE("update"),
    CANCEL("cancel");

    private static final Map<String, OrderOperationEnum> VALUES_BY_IDENTIFIER = stream(OrderOperationEnum.values()).collect(toMap(OrderOperationEnum::getValue, identity()));
    private final String value;

    OrderOperationEnum(String value) {
        this.value = value;
    }

    public static OrderOperationEnum extractValue(String value) {
        for (OrderOperationEnum orderOperationEnum : OrderOperationEnum.values()) {
            if (value.contains(orderOperationEnum.getValue())) {
                return orderOperationEnum;
            }
        }
        return OrderOperationEnum.INVALID_ORDER_OPERATION;
    }

    public static OrderOperationEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value.toLowerCase(), INVALID_ORDER_OPERATION);
    }

    public String getValue() {
        return value;
    }
}
