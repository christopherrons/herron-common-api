package com.herron.exchange.common.api.common.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum OrderExecutionTypeEnum {
    INVALID_ORDER_EXECUTION_TYPE(null),
    FOK("fill-or-kill"),
    FAK("fill-and-kill"),
    FILL("fill");

    private static final Map<String, OrderExecutionTypeEnum> VALUES_BY_IDENTIFIER = stream(OrderExecutionTypeEnum.values()).collect(toMap(OrderExecutionTypeEnum::getValue, identity()));
    private final String value;

    OrderExecutionTypeEnum(String value) {
        this.value = value;
    }

    public static OrderExecutionTypeEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value, INVALID_ORDER_EXECUTION_TYPE);
    }

    public String getValue() {
        return value;
    }
}
