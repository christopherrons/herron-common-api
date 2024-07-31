package com.herron.exchange.common.api.common.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum QuoteTypeEnum {
    BID_PRICE("BID_PRICE"),
    BID_VOLUME("BID_VOLUME"),
    LAST_PRICE("LAST_PRICE"),
    ASK_PRICE("ASK_PRICE"),
    ASK_VOLUME("ASK_VOLUME");

    private static final Map<String, QuoteTypeEnum> VALUES_BY_IDENTIFIER = stream(QuoteTypeEnum.values()).collect(toMap(QuoteTypeEnum::getValue, identity()));
    private final String value;

    QuoteTypeEnum(String value) {
        this.value = value;
    }

    public static QuoteTypeEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value, null);
    }

    public String getValue() {
        return value;
    }
}
