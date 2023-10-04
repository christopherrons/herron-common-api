package com.herron.exchange.common.api.common.messages.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.Instrument;
import com.herron.exchange.common.api.common.api.InstrumentRequest;
import com.herron.exchange.common.api.common.api.InstrumentResponse;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.RequestStatus;
import com.herron.exchange.common.api.common.messages.response.HerronInstrumentResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronInstrumentRequest(long requestId, Instrument instrument, long timeStampInMs) implements InstrumentRequest {

    public HerronInstrumentRequest(HerronInstrumentRequest instrumentRequest) {
        this(instrumentRequest.requestId, instrumentRequest.instrument, instrumentRequest.timeStampInMs);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_INSTRUMENT_REQUEST;
    }

    @Override
    public InstrumentResponse createOkResponse(long timeStampInMs,
                                               long requestId,
                                               String responseMessage) {
        return new HerronInstrumentResponse(timeStampInMs, requestId, RequestStatus.OK, responseMessage);
    }

    @Override
    public InstrumentResponse createErrorResponse(long timeStampInMs,
                                                  long requestId,
                                                  String errorMessage) {
        return new HerronInstrumentResponse(timeStampInMs, requestId, RequestStatus.ERROR, errorMessage);
    }
}
