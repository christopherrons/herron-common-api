package com.herron.exchange.common.api.common.enums.messagetypes;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.api.MessageType;
import com.herron.exchange.common.api.common.messages.trading.*;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum TradingMessageTypeEnum implements MessageType {
    STATE_CHANGE("STCH", StateChange.class),
    TRADING_CALENDAR("TRCA", TradingCalendar.class),
    LIMIT_ORDER("LIOR", LimitOrder.class),
    PRICE_QUOTE("PRQU", PriceQuote.class),
    MARKET_ORDER("MAOR", MarketOrder.class),
    TRADE("TRAD", Trade.class),
    TRADE_EXECUTION("TREX", TradeExecution.class);

    private static final Map<String, TradingMessageTypeEnum> VALUES_BY_IDENTIFIER = stream(TradingMessageTypeEnum.values())
            .collect(toMap(TradingMessageTypeEnum::getMessageTypeId, identity()));

    private final String messageTypeId;
    private final Class<? extends Message> classToBeDeserialized;

    TradingMessageTypeEnum(String messageTypeId, Class<? extends Message> classToBeDeserialized) {
        this.messageTypeId = messageTypeId;
        this.classToBeDeserialized = classToBeDeserialized;
    }

    public static TradingMessageTypeEnum getMessageTypeEnum(String messageTypeId) {
        return VALUES_BY_IDENTIFIER.getOrDefault(messageTypeId, null);
    }

    public String getMessageTypeId() {
        return messageTypeId;
    }

    public Class<? extends Message> getClassToBeDeserialized() {
        return classToBeDeserialized;
    }

    public static Map<String, Class<? extends Message>> getIdToClassImplementation() {
        return stream(TradingMessageTypeEnum.values()).collect(Collectors.toMap(TradingMessageTypeEnum::getMessageTypeId, TradingMessageTypeEnum::getClassToBeDeserialized));
    }
}
