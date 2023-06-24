package com.herron.exchange.common.api.common.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.api.StateChange;
import com.herron.exchange.common.api.common.api.StateChangeRequest;
import com.herron.exchange.common.api.common.api.StateChangeResponse;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.RequestStatus;
import com.herron.exchange.common.api.common.response.HerronStateChangeResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronStateChangeRequest(long requestId, StateChange stateChange) implements StateChangeRequest {

    public HerronStateChangeRequest(HerronStateChangeRequest stateChangeRequest) {
        this(stateChangeRequest.requestId, stateChangeRequest.stateChange);
    }

    @Override
    public Message getCopy() {
        return new HerronStateChangeRequest(this);
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
    public StateChangeResponse createResponse(long timeStampInMs,
                                              long requestId,
                                              RequestStatus requestStatus,
                                              String responseMessage) {
        return new HerronStateChangeResponse(timeStampInMs, requestId, requestStatus, responseMessage);
    }
}
