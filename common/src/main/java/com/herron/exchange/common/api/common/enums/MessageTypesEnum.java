package com.herron.exchange.common.api.common.enums;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.mapper.HerronJsonDeserializer;
import com.herron.exchange.common.api.common.messages.bitstamp.*;
import com.herron.exchange.common.api.common.messages.herron.*;

import java.util.Map;
import java.util.function.Function;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum MessageTypesEnum {

    INVALID_MESSAGE_TYPE(null, null),
    BITSTAMP_ADD_ORDER("BSAO", new HerronJsonDeserializer(BitstampAddOrder.class)::decodeMessage),
    BITSTAMP_TRADE("BSTR", new HerronJsonDeserializer(BitstampTrade.class)::decodeMessage),
    BITSTAMP_ORDERBOOK_DATA("BSOB", new HerronJsonDeserializer(BitstampOrderbookData.class)::decodeMessage),
    BITSTAMP_STOCK_INSTRUMENT("BSSI", new HerronJsonDeserializer(BitstampStockInstrument.class)::decodeMessage),
    BITSTAMP_STATE_CHANGE("BSSC", new HerronJsonDeserializer(BitstampStateChange.class)::decodeMessage),
    HERRON_ADD_ORDER("HEAO", new HerronJsonDeserializer(HerronAddOrder.class)::decodeMessage),
    HERRON_UPDATE_ORDER("HEUO", new HerronJsonDeserializer(HerronUpdateOrder.class)::decodeMessage),
    HERRON_CANCEL_ORDER("HECO", new HerronJsonDeserializer(HerronCancelOrder.class)::decodeMessage),
    HERRON_TRADE("HETR", new HerronJsonDeserializer(HerronTrade.class)::decodeMessage),
    HERRON_ORDERBOOK_DATA("HEOB", new HerronJsonDeserializer(HerronOrderbookData.class)::decodeMessage),
    HERRON_STOCK_INSTRUMENT("HESI", new HerronJsonDeserializer(HerronStockInstrument.class)::decodeMessage),
    HERRON_STATE_CHANGE("HESC", new HerronJsonDeserializer(HerronStateChange.class)::decodeMessage);

    private final String messageTypeId;
    private final Function<String, Message> messageMapper;

    MessageTypesEnum(String messageTypeId, Function<String, Message> messageMapper) {
        this.messageTypeId = messageTypeId;
        this.messageMapper = messageMapper;
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

    private static final Map<String, MessageTypesEnum> VALUES_BY_IDENTIFIER = stream(MessageTypesEnum.values())
            .collect(toMap(MessageTypesEnum::getMessageTypeId, identity()));

    public static MessageTypesEnum getMessageTypeEnum(String messageTypeId) {
        return VALUES_BY_IDENTIFIER.getOrDefault(messageTypeId, INVALID_MESSAGE_TYPE);
    }

    public static Message decodeMessage(String messageTypeId, String message) {
        return VALUES_BY_IDENTIFIER.getOrDefault(messageTypeId, INVALID_MESSAGE_TYPE).decodeMessage(message);
    }
}
