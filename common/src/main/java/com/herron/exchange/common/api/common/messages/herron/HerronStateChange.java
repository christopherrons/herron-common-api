package com.herron.exchange.common.api.common.messages.herron;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.api.StateChange;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.StateChangeTypeEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronStateChange(@JsonProperty("orderbookId") String orderbookId,
                                @JsonProperty("stateChange") StateChangeTypeEnum stateChangeType,
                                @JsonProperty("timeStampInMs") long timeStampInMs) implements StateChange {

    public HerronStateChange(StateChange stateChange) {
        this(stateChange.orderbookId(),
                stateChange.stateChangeType(),
                stateChange.timeStampInMs());
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_STATE_CHANGE;
    }

}
