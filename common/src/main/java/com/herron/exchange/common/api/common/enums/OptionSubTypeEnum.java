package com.herron.exchange.common.api.common.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum OptionSubTypeEnum {
    OOF("option on future"),
    OOE("option on equity");

    private static final Map<String, OptionSubTypeEnum> VALUES_BY_IDENTIFIER = stream(OptionSubTypeEnum.values()).collect(toMap(OptionSubTypeEnum::getValue, identity()));
    private final String value;

    OptionSubTypeEnum(String value) {
        this.value = value;
    }

    public static OptionSubTypeEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value, null);
    }

    public String getValue() {
        return value;
    }
}
