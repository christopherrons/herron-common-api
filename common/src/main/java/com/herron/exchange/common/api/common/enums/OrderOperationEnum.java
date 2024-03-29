package com.herron.exchange.common.api.common.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum OrderOperationEnum {
    INSERT("insert/created"),
    UPDATE("update"),
    CANCEL("cancel/delete");

    private static final Map<String, OrderOperationEnum> VALUES_BY_IDENTIFIER = stream(OrderOperationEnum.values()).collect(toMap(OrderOperationEnum::getValue, identity()));
    private final String value;

    OrderOperationEnum(String value) {
        this.value = value;
    }

    public static OrderOperationEnum extractValue(String value) {
        for (OrderOperationEnum orderOperationEnum : OrderOperationEnum.values()) {
            if (orderOperationEnum.getValue().contains(value)) {
                return orderOperationEnum;
            }
        }
        return null;
    }

    public static OrderOperationEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value.toLowerCase(), null);
    }

    public String getValue() {
        return value;
    }
}
