package com.herron.exchange.common.api.common.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.api.Order;
import com.herron.exchange.common.api.common.api.OrderRequest;
import com.herron.exchange.common.api.common.api.OrderResponse;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.RequestStatus;
import com.herron.exchange.common.api.common.response.HerronOrderResponse;

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
    public OrderResponse createResponse(long timeStampInMs,
                                        long requestId,
                                        RequestStatus requestStatus,
                                        String responseMessage) {
        return new HerronOrderResponse(timeStampInMs, requestId, requestStatus, responseMessage);
    }
}
