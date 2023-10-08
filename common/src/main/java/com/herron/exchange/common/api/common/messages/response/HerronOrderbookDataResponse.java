package com.herron.exchange.common.api.common.messages.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.broadcasts.response.OrderbookDataResponse;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.RequestStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronOrderbookDataResponse(long timeStampInMs,
                                          long requestId,
                                          RequestStatus requestStatus,
                                          String responseMessage) implements OrderbookDataResponse {

    public HerronOrderbookDataResponse(HerronOrderbookDataResponse orderbookDataResponse) {
        this(orderbookDataResponse.timeStampInMs, orderbookDataResponse.requestId, orderbookDataResponse.requestStatus, orderbookDataResponse.responseMessage);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_ORDERBOOK_DATA_RESPONSE;
    }

}
