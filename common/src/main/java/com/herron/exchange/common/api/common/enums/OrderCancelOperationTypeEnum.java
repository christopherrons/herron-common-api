package com.herron.exchange.common.api.common.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.Map;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum OrderCancelOperationTypeEnum {
    INVALID_CANCEL_TYPE(null),
    SELF_MATCH("self-match"),
    FILLED("filled"),
    KILLED("killed"),
    FAK("filled-and-killed");

    private static final Map<String, OrderCancelOperationTypeEnum> VALUES_BY_IDENTIFIER = Arrays.stream(OrderCancelOperationTypeEnum.values()).collect(toMap(OrderCancelOperationTypeEnum::getValue, identity()));
    private final String value;

    OrderCancelOperationTypeEnum(String value) {
        this.value = value;
    }

    public static OrderCancelOperationTypeEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value, INVALID_CANCEL_TYPE);
    }

    public String getValue() {
        return value;
    }
}
