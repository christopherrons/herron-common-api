package com.herron.exchange.common.api.common.messages.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.OrderbookData;
import com.herron.exchange.common.api.common.api.OrderbookDataRequest;
import com.herron.exchange.common.api.common.api.OrderbookDataResponse;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.RequestStatus;
import com.herron.exchange.common.api.common.messages.response.HerronOrderbookDataResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronOrderbookDataRequest(long requestId, OrderbookData orderbookData, long timeStampInMs) implements OrderbookDataRequest {

    public HerronOrderbookDataRequest(HerronOrderbookDataRequest orderbookDataRequest) {
        this(orderbookDataRequest.requestId, orderbookDataRequest.orderbookData, orderbookDataRequest.timeStampInMs);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_ORDERBOOK_DATA_REQUEST;
    }

    @Override
    public OrderbookDataResponse createOkResponse(long timeStampInMs,
                                                  long requestId,
                                                  String responseMessage) {
        return new HerronOrderbookDataResponse(timeStampInMs, requestId, RequestStatus.OK, responseMessage);
    }

    @Override
    public OrderbookDataResponse createErrorResponse(long timeStampInMs,
                                                     long requestId,
                                                     String errorMessage) {
        return new HerronOrderbookDataResponse(timeStampInMs, requestId, RequestStatus.ERROR, errorMessage);
    }
}
