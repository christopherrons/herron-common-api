package com.herron.exchange.common.api.common.enums;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum StateChangeEnum {
    INVALID_STATE_CHANGE("Invalid State Change"),
    CONTINUOUS_TRADING("continuous trading");

    private static final Map<String, StateChangeEnum> VALUES_BY_IDENTIFIER = stream(StateChangeEnum.values()).collect(toMap(StateChangeEnum::getValue, identity()));
    private final String value;

    StateChangeEnum(String value) {
        this.value = value;
    }

    public static StateChangeEnum extractValue(String value) {
        for (StateChangeEnum stateChangeEnum : StateChangeEnum.values()) {
            if (value.contains(stateChangeEnum.getValue())) {
                return stateChangeEnum;
            }
        }
        return StateChangeEnum.INVALID_STATE_CHANGE;
    }

    public static StateChangeEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value.toLowerCase(), INVALID_STATE_CHANGE);
    }

    public String getValue() {
        return value;
    }
}
