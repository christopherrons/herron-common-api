package com.herron.exchange.common.api.common.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum StateChangeTypeEnum {
    INVALID_STATE_CHANGE(null),
    CONTINUOUS_TRADING("continuous trading");

    private static final Map<String, StateChangeTypeEnum> VALUES_BY_IDENTIFIER = stream(StateChangeTypeEnum.values()).collect(toMap(StateChangeTypeEnum::getValue, identity()));
    private final String value;

    StateChangeTypeEnum(String value) {
        this.value = value;
    }

    public static StateChangeTypeEnum extractValue(String value) {
        for (StateChangeTypeEnum stateChangeTypeEnum : StateChangeTypeEnum.values()) {
            if (value.contains(stateChangeTypeEnum.getValue())) {
                return stateChangeTypeEnum;
            }
        }
        return StateChangeTypeEnum.INVALID_STATE_CHANGE;
    }

    public static StateChangeTypeEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value.toLowerCase(), INVALID_STATE_CHANGE);
    }

    public String getValue() {
        return value;
    }
}
