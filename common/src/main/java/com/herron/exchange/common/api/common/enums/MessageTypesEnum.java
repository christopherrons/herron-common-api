package com.herron.exchange.common.api.common.enums;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.mapper.HerronJsonMapperUtil;
import com.herron.exchange.common.api.common.messages.HerronBroadcastMessage;
import com.herron.exchange.common.api.common.messages.common.HerronDataLoading;
import com.herron.exchange.common.api.common.messages.refdata.HerronBondInstrument;
import com.herron.exchange.common.api.common.messages.refdata.HerronMarket;
import com.herron.exchange.common.api.common.messages.refdata.HerronOrderbookData;
import com.herron.exchange.common.api.common.messages.refdata.HerronStockInstrument;
import com.herron.exchange.common.api.common.messages.request.HerronInstrumentRequest;
import com.herron.exchange.common.api.common.messages.request.HerronOrderRequest;
import com.herron.exchange.common.api.common.messages.request.HerronOrderbookDataRequest;
import com.herron.exchange.common.api.common.messages.request.HerronStateChangeRequest;
import com.herron.exchange.common.api.common.messages.response.*;
import com.herron.exchange.common.api.common.messages.trading.*;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum MessageTypesEnum {

    INVALID_MESSAGE_TYPE(null, null),
    HERRON_ORDERBOOK_DATA_REQUEST("HODREQ", HerronOrderbookDataRequest.class),
    HERRON_ORDERBOOK_DATA_RESPONSE("HODREP", HerronOrderbookDataResponse.class),
    HERRON_INSTRUMENT_REQUEST("HINREQ", HerronInstrumentRequest.class),
    HERRON_INSTRUMENT_RESPONSE("HINREP", HerronInstrumentResponse.class),
    HERRON_ORDER_REQUEST("HORREQ", HerronOrderRequest.class),
    HERRON_ORDER_RESPONSE("HORREP", HerronOrderResponse.class),
    HERRON_STATE_CHANGE_REQUEST("HSTREQ", HerronStateChangeRequest.class),
    HERRON_STATE_CHANGE_RESPONSE("HSTREP", HerronStateChangeResponse.class),
    INVALID_REQUEST_RESPONSE("IREQREP", InvalidRequestResponse.class),
    BITSTAMP_BROADCAST_MESSAGE("BSBM", HerronBroadcastMessage.class),
    BITSTAMP_ADD_ORDER("BSAO", HerronAddOrder.class),
    BITSTAMP_UPDATE_ORDER("BSUO", HerronUpdateOrder.class),
    BITSTAMP_CANCEL_ORDER("BSCO", HerronCancelOrder.class),
    BITSTAMP_TRADE("BSTR", HerronTrade.class),
    BITSTAMP_ORDERBOOK_DATA("BSOB", HerronOrderbookData.class),
    BITSTAMP_STOCK_INSTRUMENT("BSSI", HerronStockInstrument.class),
    BITSTAMP_STATE_CHANGE("BSSC", HerronStateChange.class),
    HERRON_BROADCAST_MESSAGE("HEBM", HerronBroadcastMessage.class),
    HERRON_ADD_ORDER("HEAO", HerronAddOrder.class),
    HERRON_UPDATE_ORDER("HEUO", HerronUpdateOrder.class),
    HERRON_CANCEL_ORDER("HECO", HerronCancelOrder.class),
    HERRON_TRADE("HETR", HerronTrade.class),
    HERRON_ORDERBOOK_DATA("HEOB", HerronOrderbookData.class),
    HERRON_STOCK_INSTRUMENT("HESI", HerronStockInstrument.class),
    HERRON_BOND_INSTRUMENT("HEBI", HerronBondInstrument.class),
    HERRON_TRADE_EXECUTION("HEEX", HerronTradeExecution.class),
    HERRON_DATA_LOADING_START("HEDL", HerronDataLoading.class),
    HERRON_MARKET("HEMA", HerronMarket.class),
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
