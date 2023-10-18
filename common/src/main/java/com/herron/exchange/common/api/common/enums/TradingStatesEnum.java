package com.herron.exchange.common.api.common.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum TradingStatesEnum {
    INVALID_TRADING_STATE(null),
    CLOSED("closed"),
    PRE_TRADE("pre trade"),
    POST_TRADE("post trade"),
    TRADE_HALT("trade halt"),
    TRADE_HALT_ENDED("trade halt ended"),
    OPEN_AUCTION_TRADING("open auction trading"),
    CLOSING_AUCTION_TRADING("closing auction trading"),
    OPEN_AUCTION_RUN("open auction run"),
    CLOSING_AUCTION_RUN("closing auction run"),
    CONTINUOUS_TRADING("continuous trading");

    private static final Map<String, TradingStatesEnum> VALUES_BY_IDENTIFIER = stream(TradingStatesEnum.values()).collect(toMap(TradingStatesEnum::getValue, identity()));
    private static final Map<TradingStatesEnum, Set<TradingStatesEnum>> fromStateToState = Map.of(
            CLOSED, Set.of(CLOSED, PRE_TRADE),
            PRE_TRADE, Set.of(TRADE_HALT, OPEN_AUCTION_TRADING, CONTINUOUS_TRADING),
            POST_TRADE, Set.of(CLOSED),
            TRADE_HALT, Set.of(TRADE_HALT_ENDED),
            TRADE_HALT_ENDED, Set.of(CLOSED, OPEN_AUCTION_TRADING, OPEN_AUCTION_RUN, CLOSING_AUCTION_RUN, CLOSING_AUCTION_TRADING, POST_TRADE, PRE_TRADE),
            OPEN_AUCTION_TRADING, Set.of(TRADE_HALT, OPEN_AUCTION_RUN),
            CLOSING_AUCTION_TRADING, Set.of(TRADE_HALT, CLOSING_AUCTION_RUN),
            OPEN_AUCTION_RUN, Set.of(TRADE_HALT, CONTINUOUS_TRADING),
            CLOSING_AUCTION_RUN, Set.of(TRADE_HALT, POST_TRADE, CLOSED),
            CONTINUOUS_TRADING, Set.of(TRADE_HALT, CLOSING_AUCTION_RUN, CLOSING_AUCTION_TRADING)
    );

    private final String value;

    TradingStatesEnum(String value) {
        this.value = value;
    }

    public static TradingStatesEnum extractValue(String value) {
        for (TradingStatesEnum tradingStatesEnum : TradingStatesEnum.values()) {
            if (value.contains(tradingStatesEnum.getValue())) {
                return tradingStatesEnum;
            }
        }
        return TradingStatesEnum.INVALID_TRADING_STATE;
    }

    public static TradingStatesEnum fromValue(String value) {
        return VALUES_BY_IDENTIFIER.getOrDefault(value.toLowerCase(), INVALID_TRADING_STATE);
    }

    public String getValue() {
        return value;
    }

    public boolean isValidStateChange(TradingStatesEnum toState) {
        return fromStateToState.get(this).contains(toState);
    }
}
