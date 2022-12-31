package com.herron.exchange.common.api.common.messages.herron;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.StateChange;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.enums.StateChangeTypeEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronStateChange(String orderbookId,
                                StateChangeTypeEnum stateChangeType,
                                long timeStampInMs) implements StateChange {

    public HerronStateChange(StateChange stateChange) {
        this(stateChange.orderbookId(),
                stateChange.stateChangeType(),
                stateChange.timeStampInMs());
    }

    @Override
    public HerronStateChange getCopy() {
        return new HerronStateChange(this);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_STATE_CHANGE;
    }

}
