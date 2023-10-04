package com.herron.exchange.common.api.common.messages.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.StateChange;
import com.herron.exchange.common.api.common.api.StateChangeRequest;
import com.herron.exchange.common.api.common.api.StateChangeResponse;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.RequestStatus;
import com.herron.exchange.common.api.common.messages.response.HerronStateChangeResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronStateChangeRequest(long requestId, StateChange stateChange) implements StateChangeRequest {

    public HerronStateChangeRequest(HerronStateChangeRequest stateChangeRequest) {
        this(stateChangeRequest.requestId, stateChangeRequest.stateChange);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_STATE_CHANGE_REQUEST;
    }

    @Override
    public long timeStampInMs() {
        return stateChange.timeStampInMs();
    }

    @Override
    public StateChangeResponse createOkResponse(long timeStampInMs,
                                                long requestId,
                                                String responseMessage) {
        return new HerronStateChangeResponse(timeStampInMs, requestId, RequestStatus.OK, responseMessage);
    }

    @Override
    public StateChangeResponse createErrorResponse(long timeStampInMs,
                                                   long requestId,
                                                   String errorMessage) {
        return new HerronStateChangeResponse(timeStampInMs, requestId, RequestStatus.ERROR, errorMessage);
    }
}
