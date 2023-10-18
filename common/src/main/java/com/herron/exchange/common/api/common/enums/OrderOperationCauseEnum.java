package com.herron.exchange.common.api.common.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum OrderOperationCauseEnum {
    SELF_MATCH("self-match"),
    FILLED("filled"),
    KILLED("killed"),
    FAK("filled-and-killed"),
    PARTIAL_FILL("partial-fill"),
    EXTERNAL_UPDATE("external-update"),
    NEW_ORDER("new-order");

    private static final Map<String, OrderOperationCauseEnum> VALUES_BY_IDENTIFIER = stream(OrderOperationCauseEnum.values()).collect(toMap(OrderOperationCauseEnum::getValue, identity()));
    private final String value;

    OrderOperationCauseEnum(String value) {
        this.value = value;
    }

    public static OrderOperationCauseEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value, null);
    }

    public String getValue() {
        return value;
    }
}
