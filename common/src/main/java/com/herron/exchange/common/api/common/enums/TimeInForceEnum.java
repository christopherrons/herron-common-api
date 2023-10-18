package com.herron.exchange.common.api.common.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum TimeInForceEnum {
    INVALID_ORDER_EXECUTION_TYPE(null),
    ICEBERG("iceberg-order"),
    GTD("good-til-date"),
    GTC("good-til-cancel"),
    FOK("fill-or-kill"),
    FAK("fill-and-kill"),
    FILL("fill");

    private static final Map<String, TimeInForceEnum> VALUES_BY_IDENTIFIER = stream(TimeInForceEnum.values()).collect(toMap(TimeInForceEnum::getValue, identity()));
    private final String value;

    TimeInForceEnum(String value) {
        this.value = value;
    }

    public static TimeInForceEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value, INVALID_ORDER_EXECUTION_TYPE);
    }

    public String getValue() {
        return value;
    }
}
