package com.herron.exchange.common.api.common.messages.bitstamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.api.StateChange;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.StateChangeTypeEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BitstampStateChange(@JsonProperty("orderbookId") String orderbookId,
                                  @JsonProperty("stateChange") String stateChangeTypeString,
                                  @JsonProperty("timeStampInMs") long timeStampInMs) implements StateChange {

    public BitstampStateChange(BitstampStateChange stateChange) {
        this(stateChange.orderbookId(),
                stateChange.stateChangeTypeString(),
                stateChange.timeStampInMs());
    }

    @Override
    public Message getCopy() {
        return new BitstampStateChange(this);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.BITSTAMP_STATE_CHANGE;
    }


    @Override
    public StateChangeTypeEnum stateChangeType() {
        return StateChangeTypeEnum.fromValue(stateChangeTypeString);
    }
}
