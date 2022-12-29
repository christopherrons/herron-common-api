package com.herron.exchange.common.api.common.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.api.StateChange;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.StateChangeEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BitstampStateChange(@JsonProperty("orderbookId") String orderbookId,
                                  @JsonProperty("stateChange") String stateChange,
                                  @JsonProperty("timeStampInMs") long timeStampInMs) implements StateChange {
    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.BITSTAMP_STATE_CHANGE;
    }


    @Override
    public StateChangeEnum getStateChangeEnum() {
        return StateChangeEnum.fromValue(stateChange);
    }
}
