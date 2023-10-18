package com.herron.exchange.common.api.common.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum OptionTypeEnum {
    PUT("PUT"),
    CALL("CALL");

    private static final Map<String, OptionTypeEnum> VALUES_BY_IDENTIFIER = stream(OptionTypeEnum.values()).collect(toMap(OptionTypeEnum::getValue, identity()));
    private final String value;

    OptionTypeEnum(String value) {
        this.value = value;
    }

    public static OptionTypeEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value, null);
    }

    public String getValue() {
        return value;
    }
}
