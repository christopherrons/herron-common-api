package com.herron.exchange.common.api.common.messages.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.*;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.RequestStatus;
import com.herron.exchange.common.api.common.messages.response.HerronOrderResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronOrderRequest(long requestId, Order order) implements OrderRequest {

    public HerronOrderRequest(HerronOrderRequest orderRequest) {
        this(orderRequest.requestId, orderRequest.order);
    }

    @Override
    public Message getCopy() {
        return new HerronOrderRequest(this);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_ORDER_REQUEST;
    }

    @Override
    public long timeStampInMs() {
        return order.timeStampInMs();
    }

    @Override
    public OrderResponse createOkResponse(long timeStampInMs,
                                          long requestId,
                                          String responseMessage) {
        return new HerronOrderResponse(timeStampInMs, requestId, RequestStatus.OK, responseMessage);
    }

    @Override
    public OrderResponse createErrorResponse(long timeStampInMs,
                                                     long requestId,
                                                     String errorMessage) {
        return new HerronOrderResponse(timeStampInMs, requestId, RequestStatus.ERROR, errorMessage);
    }
}
