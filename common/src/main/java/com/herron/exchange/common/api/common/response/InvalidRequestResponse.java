package com.herron.exchange.common.api.common.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.InstrumentResponse;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.RequestStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InvalidRequestResponse(long timeStampInMs,
                                     long requestId,
                                     String responseMessage) implements InstrumentResponse {

    public InvalidRequestResponse(InvalidRequestResponse invalidRequestResponse) {
        this(invalidRequestResponse.timeStampInMs, invalidRequestResponse.requestId, invalidRequestResponse.responseMessage);
    }

    @Override
    public Message getCopy() {
        return new InvalidRequestResponse(this);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_INSTRUMENT_RESPONSE;
    }

    @Override
    public RequestStatus requestStatus() {
        return RequestStatus.ERROR;
    }
}
