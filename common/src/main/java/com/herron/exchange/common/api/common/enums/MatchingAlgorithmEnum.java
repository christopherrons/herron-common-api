package com.herron.exchange.common.api.common.enums;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum MatchingAlgorithmEnum {
    INVALID_MATCHING_ALGORITHM(null),
    FIFO("fifo");

    private static final Map<String, MatchingAlgorithmEnum> VALUES_BY_IDENTIFIER = stream(MatchingAlgorithmEnum.values()).collect(toMap(MatchingAlgorithmEnum::getValue, identity()));
    private final String value;

    MatchingAlgorithmEnum(String value) {
        this.value = value;
    }

    public static MatchingAlgorithmEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value.toLowerCase(), INVALID_MATCHING_ALGORITHM);
    }

    public String getValue() {
        return value;
    }
}
