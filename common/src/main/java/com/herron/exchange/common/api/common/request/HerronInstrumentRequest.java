package com.herron.exchange.common.api.common.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.*;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.RequestStatus;
import com.herron.exchange.common.api.common.response.HerronInstrumentResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronInstrumentRequest(long requestId, Instrument instrument, long timeStampInMs) implements InstrumentRequest {

    public HerronInstrumentRequest(HerronInstrumentRequest instrumentRequest) {
        this(instrumentRequest.requestId, instrumentRequest.instrument, instrumentRequest.timeStampInMs);
    }

    @Override
    public Message getCopy() {
        return new HerronInstrumentRequest(this);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_INSTRUMENT_REQUEST;
    }

    @Override
    public InstrumentResponse createResponse(long timeStampInMs,
                                   long requestId,
                                   RequestStatus requestStatus,
                                   String responseMessage) {
        return new HerronInstrumentResponse(timeStampInMs, requestId, requestStatus, responseMessage);
    }
}
