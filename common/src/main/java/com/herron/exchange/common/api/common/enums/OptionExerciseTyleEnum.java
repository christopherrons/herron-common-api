package com.herron.exchange.common.api.common.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum OptionExerciseTyleEnum {
    EUROPEAN("EUROPEAN"),
    AMERICAN("AMERICAN");

    private static final Map<String, OptionExerciseTyleEnum> VALUES_BY_IDENTIFIER = stream(OptionExerciseTyleEnum.values()).collect(toMap(OptionExerciseTyleEnum::getValue, identity()));
    private final String value;

    OptionExerciseTyleEnum(String value) {
        this.value = value;
    }

    public static OptionExerciseTyleEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value, null);
    }

    public String getValue() {
        return value;
    }
}
