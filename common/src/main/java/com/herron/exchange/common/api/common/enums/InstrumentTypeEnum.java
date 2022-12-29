package com.herron.exchange.common.api.common.enums;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum InstrumentTypeEnum {
    INVALID_INSTRUMENT_TYPE(null),
    STOCK("stock"),
    OPTION("option"),
    SPREAD("spread"),
    FUTURE("future");

    private static final Map<String, InstrumentTypeEnum> VALUES_BY_IDENTIFIER = stream(InstrumentTypeEnum.values()).collect(toMap(InstrumentTypeEnum::getValue, identity()));
    private final String value;

    InstrumentTypeEnum(String value) {
        this.value = value;
    }

    public static InstrumentTypeEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value.toLowerCase(), INVALID_INSTRUMENT_TYPE);
    }

    public String getValue() {
        return value;
    }
}
