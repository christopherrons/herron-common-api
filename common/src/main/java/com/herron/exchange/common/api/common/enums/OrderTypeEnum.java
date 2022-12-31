package com.herron.exchange.common.api.common.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum OrderTypeEnum {
    INVALID_ORDER_TYPE(null),
    LIMIT("limit"),
    MARKET("market");

    private static final Map<String, OrderTypeEnum> VALUES_BY_IDENTIFIER = stream(OrderTypeEnum.values()).collect(toMap(OrderTypeEnum::getValue, identity()));
    private final String value;

    OrderTypeEnum(String value) {
        this.value = value;
    }

    public static OrderTypeEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value, INVALID_ORDER_TYPE);
    }

    public String getValue() {
        return value;
    }
}
