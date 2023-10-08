package com.herron.exchange.common.api.common.messages.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.broadcasts.response.InstrumentResponse;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.RequestStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronInstrumentResponse(long timeStampInMs,
                                       long requestId,
                                       RequestStatus requestStatus,
                                       String responseMessage) implements InstrumentResponse {

    public HerronInstrumentResponse(HerronInstrumentResponse instrumentResponse) {
        this(instrumentResponse.timeStampInMs, instrumentResponse.requestId, instrumentResponse.requestStatus, instrumentResponse.responseMessage);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_INSTRUMENT_RESPONSE;
    }

}
