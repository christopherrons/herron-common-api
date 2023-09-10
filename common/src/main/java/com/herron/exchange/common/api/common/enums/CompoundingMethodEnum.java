package com.herron.exchange.common.api.common.enums;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum CompoundingMethodEnum {

    INVALID_COMPOUNDING_METHOD(null),
    SIMPLE("simple"),
    CONTINUOUS("continuous"),
    COMPOUNDING("compounding");

    private static final Map<String, CompoundingMethodEnum> VALUES_BY_IDENTIFIER = stream(CompoundingMethodEnum.values()).collect(toMap(CompoundingMethodEnum::getValue, identity()));
    private final String value;

    CompoundingMethodEnum(String value) {
        this.value = value;
    }

    public static CompoundingMethodEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value, INVALID_COMPOUNDING_METHOD);
    }

    public String getValue() {
        return value;
    }
}
