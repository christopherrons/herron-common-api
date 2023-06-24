package com.herron.exchange.common.api.common.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.api.OrderbookData;
import com.herron.exchange.common.api.common.api.OrderbookDataRequest;
import com.herron.exchange.common.api.common.api.OrderbookDataResponse;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.RequestStatus;
import com.herron.exchange.common.api.common.response.HerronOrderbookDataResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronOrderbookDataRequest(long requestId, OrderbookData orderbookData) implements OrderbookDataRequest {

    public HerronOrderbookDataRequest(HerronOrderbookDataRequest orderbookDataRequest) {
        this(orderbookDataRequest.requestId, orderbookDataRequest.orderbookData);
    }

    @Override
    public Message getCopy() {
        return new HerronOrderbookDataRequest(this);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_ORDERBOOK_DATA_REQUEST;
    }

    @Override
    public long timeStampInMs() {
        return orderbookData.timeStampInMs();
    }

    @Override
    public OrderbookDataResponse createResponse(long timeStampInMs,
                                                long requestId,
                                                RequestStatus requestStatus,
                                                String responseMessage) {
        return new HerronOrderbookDataResponse(timeStampInMs, requestId, requestStatus, responseMessage);
    }
}
