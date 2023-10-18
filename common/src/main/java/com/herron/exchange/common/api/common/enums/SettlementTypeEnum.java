package com.herron.exchange.common.api.common.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum SettlementTypeEnum {
    CASH("CASH"),
    PHYSICAL("PHYSICAL");

    private static final Map<String, SettlementTypeEnum> VALUES_BY_IDENTIFIER = stream(SettlementTypeEnum.values()).collect(toMap(SettlementTypeEnum::getValue, identity()));
    private final String value;

    SettlementTypeEnum(String value) {
        this.value = value;
    }

    public static SettlementTypeEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value, null);
    }

    public String getValue() {
        return value;
    }
}
