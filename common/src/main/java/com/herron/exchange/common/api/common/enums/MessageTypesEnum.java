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

    INVALID_MESSAGE_TYPE(null, null, null),
    BITSTAMP_ADD_ORDER("BSAO", new HerronJsonMapper(BitstampAddOrder.class)::deserializeMessage, new HerronJsonMapper(BitstampAddOrder.class)::serializeMessage),
    BITSTAMP_TRADE("BSTR", new HerronJsonMapper(BitstampTrade.class)::deserializeMessage, new HerronJsonMapper(BitstampTrade.class)::serializeMessage),
    BITSTAMP_ORDERBOOK_DATA("BSOB", new HerronJsonMapper(BitstampOrderbookData.class)::deserializeMessage, new HerronJsonMapper(BitstampOrderbookData.class)::serializeMessage),
    BITSTAMP_STOCK_INSTRUMENT("BSSI", new HerronJsonMapper(BitstampStockInstrument.class)::deserializeMessage, new HerronJsonMapper(BitstampStockInstrument.class)::serializeMessage),
    BITSTAMP_STATE_CHANGE("BSSC", new HerronJsonMapper(BitstampStateChange.class)::deserializeMessage, new HerronJsonMapper(BitstampStateChange.class)::serializeMessage),
    HERRON_BROADCAST_MESSAGE("HEBM", new HerronJsonMapper(HerronBroadcastMessage.class)::deserializeMessage, new HerronJsonMapper(HerronBroadcastMessage.class)::serializeMessage),
    HERRON_ADD_ORDER("HEAO", new HerronJsonMapper(HerronAddOrder.class)::deserializeMessage, new HerronJsonMapper(HerronAddOrder.class)::serializeMessage),
    HERRON_UPDATE_ORDER("HEUO", new HerronJsonMapper(HerronUpdateOrder.class)::deserializeMessage, new HerronJsonMapper(HerronUpdateOrder.class)::serializeMessage),
    HERRON_CANCEL_ORDER("HECO", new HerronJsonMapper(HerronCancelOrder.class)::deserializeMessage, new HerronJsonMapper(HerronCancelOrder.class)::serializeMessage),
    HERRON_TRADE("HETR", new HerronJsonMapper(HerronTrade.class)::deserializeMessage, new HerronJsonMapper(HerronTrade.class)::serializeMessage),
    HERRON_ORDERBOOK_DATA("HEOB", new HerronJsonMapper(HerronOrderbookData.class)::deserializeMessage, new HerronJsonMapper(HerronOrderbookData.class)::serializeMessage),
    HERRON_STOCK_INSTRUMENT("HESI", new HerronJsonMapper(HerronStockInstrument.class)::deserializeMessage, new HerronJsonMapper(HerronStockInstrument.class)::serializeMessage),
    HERRON_STATE_CHANGE("HESC", new HerronJsonMapper(HerronStateChange.class)::deserializeMessage, new HerronJsonMapper(HerronStateChange.class)::serializeMessage);

    private static final Map<String, MessageTypesEnum> VALUES_BY_IDENTIFIER = stream(MessageTypesEnum.values())
            .collect(toMap(MessageTypesEnum::getMessageTypeId, identity()));
    private final String messageTypeId;
    private final Function<String, Message> messageDeserializer;
    private final Function<Message, String> messageSerializer;

    MessageTypesEnum(String messageTypeId, Function<String, Message> messageDeserializer, Function<Message, String> messageSerializer) {
        this.messageTypeId = messageTypeId;
        this.messageDeserializer = messageDeserializer;
        this.messageSerializer = messageSerializer;
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

    public Message deserializeMessage(String message) {
        if (messageDeserializer == null) {
            return null;
        }
        return messageDeserializer.apply(message);
    }

    public String serializeMessage(Message message) {
        if (messageSerializer == null) {
            return null;
        }
        return messageSerializer.apply(message);
    }
}
