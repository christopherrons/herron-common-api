package com.herron.exchange.common.api.common.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum TradingSubStatesEnum {
    INVALID_TRADING_SUB_STATE(null),
    KNOCK_OUT("knock-out"),
    BUYBACK("buyback"),
    DISTRIBUTION("distribution"),
    RECALCULATED("recalculated");

    private static final Map<String, TradingSubStatesEnum> VALUES_BY_IDENTIFIER = stream(TradingSubStatesEnum.values()).collect(toMap(TradingSubStatesEnum::getValue, identity()));

    private final String value;

    TradingSubStatesEnum(String value) {
        this.value = value;
    }

    public static TradingSubStatesEnum extractValue(String value) {
        for (TradingSubStatesEnum tradingStatesEnum : TradingSubStatesEnum.values()) {
            if (value.contains(tradingStatesEnum.getValue())) {
                return tradingStatesEnum;
            }
        }
        return TradingSubStatesEnum.INVALID_TRADING_SUB_STATE;
    }

    public static TradingSubStatesEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value.toLowerCase(), INVALID_TRADING_SUB_STATE);
    }

    public String getValue() {
        return value;
    }
}
