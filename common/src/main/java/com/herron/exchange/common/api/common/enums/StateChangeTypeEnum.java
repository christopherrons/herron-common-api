package com.herron.exchange.common.api.common.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum StateChangeTypeEnum {
    INVALID_STATE_CHANGE(null),
    CLOSED("closed"),
    PRE_TRADE("pre trade"),
    POST_TRADE("post trade"),
    TRADE_STOP("trade stop"),
    OPEN_AUCTION_TRADING("open auction trading"),
    CLOSING_AUCTION_TRADING("closing auction trading"),
    OPEN_AUCTION_RUN("open auction run"),
    CLOSING_AUCTION_RUN("closing auction run"),
    CONTINUOUS_TRADING("continuous trading");

    private static final Map<String, StateChangeTypeEnum> VALUES_BY_IDENTIFIER = stream(StateChangeTypeEnum.values()).collect(toMap(StateChangeTypeEnum::getValue, identity()));
    private static final Map<StateChangeTypeEnum, Set<StateChangeTypeEnum>> fromStateToState = Map.of(
            CLOSED, Set.of(CLOSED, PRE_TRADE),
            PRE_TRADE, Set.of(TRADE_STOP, OPEN_AUCTION_TRADING, CONTINUOUS_TRADING),
            POST_TRADE, Set.of(CLOSED),
            TRADE_STOP, Set.of(CLOSED, OPEN_AUCTION_TRADING, OPEN_AUCTION_RUN, CLOSING_AUCTION_RUN, CLOSING_AUCTION_TRADING, POST_TRADE, PRE_TRADE),
            OPEN_AUCTION_TRADING, Set.of(TRADE_STOP, OPEN_AUCTION_RUN),
            CLOSING_AUCTION_TRADING, Set.of(TRADE_STOP, CLOSING_AUCTION_RUN),
            OPEN_AUCTION_RUN, Set.of(TRADE_STOP, CONTINUOUS_TRADING),
            CLOSING_AUCTION_RUN, Set.of(TRADE_STOP, POST_TRADE, CLOSED),
            CONTINUOUS_TRADING, Set.of(TRADE_STOP, CLOSING_AUCTION_RUN, CLOSING_AUCTION_TRADING)
    );

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

    public boolean isValidStateChange(StateChangeTypeEnum toState) {
        return fromStateToState.get(this).contains(toState);
    }
}
