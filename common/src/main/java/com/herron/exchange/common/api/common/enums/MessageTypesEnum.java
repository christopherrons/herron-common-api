package com.herron.exchange.common.api.common.enums;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.mapper.HerronJsonMapper;
import com.herron.exchange.common.api.common.messages.bitstamp.*;
import com.herron.exchange.common.api.common.messages.herron.*;

import java.util.Map;
import java.util.function.Function;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum MessageTypesEnum {

    INVALID_MESSAGE_TYPE(null, null),
    BITSTAMP_ADD_ORDER("BSAO", new HerronJsonMapper(BitstampAddOrder.class)::decodeMessage),
    BITSTAMP_TRADE("BSTR", new HerronJsonMapper(BitstampTrade.class)::decodeMessage),
    BITSTAMP_ORDERBOOK_DATA("BSOB", new HerronJsonMapper(BitstampOrderbookData.class)::decodeMessage),
    BITSTAMP_STOCK_INSTRUMENT("BSSI", new HerronJsonMapper(BitstampStockInstrument.class)::decodeMessage),
    BITSTAMP_STATE_CHANGE("BSSC", new HerronJsonMapper(BitstampStateChange.class)::decodeMessage),
    HERRON_ADD_ORDER("HEAO", new HerronJsonMapper(HerronAddOrder.class)::decodeMessage),
    HERRON_UPDATE_ORDER("HEUO", new HerronJsonMapper(HerronUpdateOrder.class)::decodeMessage),
    HERRON_CANCEL_ORDER("HECO", new HerronJsonMapper(HerronCancelOrder.class)::decodeMessage),
    HERRON_TRADE("HETR", new HerronJsonMapper(HerronTrade.class)::decodeMessage),
    HERRON_ORDERBOOK_DATA("HEOB", new HerronJsonMapper(HerronOrderbookData.class)::decodeMessage),
    HERRON_STOCK_INSTRUMENT("HESI", new HerronJsonMapper(HerronStockInstrument.class)::decodeMessage),
    HERRON_STATE_CHANGE("HESC", new HerronJsonMapper(HerronStateChange.class)::decodeMessage);

    private static final Map<String, MessageTypesEnum> VALUES_BY_IDENTIFIER = stream(MessageTypesEnum.values())
            .collect(toMap(MessageTypesEnum::getMessageTypeId, identity()));
    private final String messageTypeId;
    private final Function<String, Message> messageMapper;

    MessageTypesEnum(String messageTypeId, Function<String, Message> messageMapper) {
        this.messageTypeId = messageTypeId;
        this.messageMapper = messageMapper;
    }

    public static MessageTypesEnum getMessageTypeEnum(String messageTypeId) {
        return VALUES_BY_IDENTIFIER.getOrDefault(messageTypeId, INVALID_MESSAGE_TYPE);
    }

    public static Message decodeMessage(String messageTypeId, String message) {
        return VALUES_BY_IDENTIFIER.getOrDefault(messageTypeId, INVALID_MESSAGE_TYPE).decodeMessage(message);
    }

    public String getMessageTypeId() {
        return messageTypeId;
    }

    public Message decodeMessage(String message) {
        if (messageMapper == null) {
            return null;
        }
        return messageMapper.apply(message);
    }
}
