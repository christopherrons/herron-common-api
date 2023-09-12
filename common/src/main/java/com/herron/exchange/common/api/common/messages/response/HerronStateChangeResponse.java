package com.herron.exchange.common.api.common.messages.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.api.StateChangeResponse;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.RequestStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronStateChangeResponse(long timeStampInMs,
                                        long requestId,
                                        RequestStatus requestStatus,
                                        String responseMessage) implements StateChangeResponse {

    public HerronStateChangeResponse(HerronStateChangeResponse stateChangeResponse) {
        this(stateChangeResponse.timeStampInMs, stateChangeResponse.requestId, stateChangeResponse.requestStatus, stateChangeResponse.responseMessage);
    }

    @Override
    public Message getCopy() {
        return new HerronStateChangeResponse(this);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_STATE_CHANGE_RESPONSE;
    }

}
