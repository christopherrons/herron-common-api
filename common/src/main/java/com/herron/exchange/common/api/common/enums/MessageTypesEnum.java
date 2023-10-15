package com.herron.exchange.common.api.common.enums;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.mapper.HerronJsonMapperUtil;
import com.herron.exchange.common.api.common.messages.DefaultBroadcastMessage;
import com.herron.exchange.common.api.common.messages.common.HerronBusinessCalendar;
import com.herron.exchange.common.api.common.messages.common.HerronDataStreamState;
import com.herron.exchange.common.api.common.messages.marketdata.DefaultMarketDataPrice;
import com.herron.exchange.common.api.common.messages.refdata.*;
import com.herron.exchange.common.api.common.messages.trading.*;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum MessageTypesEnum {

    INVALID_MESSAGE_TYPE(null, null),
    DEFAULT_MARKET_DATA_PRICE("DMDP", DefaultMarketDataPrice.class),
    DEFAULT_BROADCAST_MESSAGE("DEBM", DefaultBroadcastMessage.class),
    HERRON_TRADING_CALENDAR("HETC", HerronTradingCalendar.class),
    HERRON_BUSINESS_CALENDAR("HEBC", HerronBusinessCalendar.class),
    HERRON_ADD_ORDER("HEAO", HerronAddOrder.class),
    HERRON_UPDATE_ORDER("HEUO", HerronUpdateOrder.class),
    HERRON_CANCEL_ORDER("HECO", HerronCancelOrder.class),
    HERRON_TRADE("HETR", HerronTrade.class),
    HERRON_ORDERBOOK_DATA("HEOB", HerronOrderbookData.class),
    HERRON_STOCK_INSTRUMENT("HESI", HerronEquityInstrument.class),
    HERRON_BOND_INSTRUMENT("HEBI", HerronBondInstrument.class),
    HERRON_OPTION_INSTRUMENT("HEOI", HerronOptionInstrument.class),
    HERRON_FUTURE_INSTRUMENT("HEFI", HerronFutureInstrument.class),
    HERRON_TRADE_EXECUTION("HEEX", HerronTradeExecution.class),
    HERRON_DATA_STREAM_STATE("HEDL", HerronDataStreamState.class),
    HERRON_MARKET("HEMA", HerronMarket.class),
    HERRON_PRODUCT("HEPR", HerronProduct.class),
    HERRON_ORDERBOOK_STATE_CHANGE("HESC", HerronStateChange.class);

    private static final Map<String, MessageTypesEnum> VALUES_BY_IDENTIFIER = stream(MessageTypesEnum.values())
            .collect(toMap(MessageTypesEnum::getMessageTypeId, identity()));

    private final String messageTypeId;
    private final Class<? extends Message> classToBeDecoded;

    MessageTypesEnum(String messageTypeId, Class<? extends Message> classToBeDecoded) {
        this.messageTypeId = messageTypeId;
        this.classToBeDecoded = classToBeDecoded;
    }

    public static MessageTypesEnum getMessageTypeEnum(String messageTypeId) {
        return VALUES_BY_IDENTIFIER.getOrDefault(messageTypeId, INVALID_MESSAGE_TYPE);
    }

    public static Message deserializeMessage(String messageTypeId, String message) {
        return VALUES_BY_IDENTIFIER.getOrDefault(messageTypeId, INVALID_MESSAGE_TYPE).deserializeMessage(message);
    }

    public String getMessageTypeId() {
        return messageTypeId;
    }

    public Message deserializeMessage(Object message) {
        if (classToBeDecoded == null) {
            return null;
        }
        return HerronJsonMapperUtil.deserializeMessage(message, classToBeDecoded);
    }

    public String serializeMessage(Message message) {
        if (message == null) {
            return null;
        }
        return HerronJsonMapperUtil.serializeMessage(message);
    }
}
