package com.herron.exchange.common.api.common.messages.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.api.OrderResponse;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.RequestStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronOrderResponse(long timeStampInMs,
                                  long requestId,
                                  RequestStatus requestStatus,
                                  String responseMessage) implements OrderResponse {

    public HerronOrderResponse(HerronOrderResponse orderResponse) {
        this(orderResponse.timeStampInMs, orderResponse.requestId, orderResponse.requestStatus, orderResponse.responseMessage);
    }

    @Override
    public Message getCopy() {
        return new HerronOrderResponse(this);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_ORDER_RESPONSE;
    }

}
