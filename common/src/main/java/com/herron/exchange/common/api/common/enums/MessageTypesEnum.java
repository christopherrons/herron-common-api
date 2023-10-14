package com.herron.exchange.common.api.common.enums;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.mapper.HerronJsonMapperUtil;
import com.herron.exchange.common.api.common.messages.ImmutableHerronBroadcastMessage;
import com.herron.exchange.common.api.common.messages.common.HerronDataLoadingState;
import com.herron.exchange.common.api.common.messages.refdata.*;
import com.herron.exchange.common.api.common.messages.trading.*;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum MessageTypesEnum {

    INVALID_MESSAGE_TYPE(null, null),
    HERRON_BROADCAST_MESSAGE("HEBM", ImmutableHerronBroadcastMessage.class),
    HERRON_ADD_ORDER("HEAO", ImmutableHerronAddOrder.class),
    HERRON_UPDATE_ORDER("HEUO", ImmutableHerronUpdateOrder.class),
    HERRON_CANCEL_ORDER("HECO", ImmutableHerronCancelOrder.class),
    HERRON_TRADE("HETR", ImmutableHerronTrade.class),
    HERRON_ORDERBOOK_DATA("HEOB", ImmutableHerronOrderbookData.class),
    HERRON_STOCK_INSTRUMENT("HESI", ImmutableHerronEquityInstrument.class),
    HERRON_BOND_INSTRUMENT("HEBI", ImmutableHerronBondInstrument.class),
    HERRON_OPTION_INSTRUMENT("HEOI", ImmutableHerronOptionInstrument.class),
    HERRON_FUTURE_INSTRUMENT("HEFI", ImmutableHerronFutureInstrument.class),
    HERRON_TRADE_EXECUTION("HEEX", ImmutableHerronTradeExecution.class),
    HERRON_DATA_LOADING_STATE("HEDL", HerronDataLoadingState.class),
    HERRON_MARKET("HEMA", ImmutableHerronMarket.class),
    HERRON_PRODUCT("HEPR", ImmutableHerronProduct.class),
    HERRON_ORDERBOOK_STATE_CHANGE("HESC", ImmutableHerronStateChange.class);

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
