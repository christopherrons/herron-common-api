package com.herron.exchange.common.api.common.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum AuctionAlgorithmEnum {
    DUTCH("dutch auction"),
    AMERICAN("american auction");

    private static final Map<String, AuctionAlgorithmEnum> VALUES_BY_IDENTIFIER = stream(AuctionAlgorithmEnum.values()).collect(toMap(AuctionAlgorithmEnum::getValue, identity()));
    private final String value;

    AuctionAlgorithmEnum(String value) {
        this.value = value;
    }

    public static AuctionAlgorithmEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value.toLowerCase(), null);
    }

    public String getValue() {
        return value;
    }
}
