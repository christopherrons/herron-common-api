package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.broadcasts.DataLoadingState;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableHerronDataLoadingState.Builder.class)
public interface HerronDataLoadingState extends DataLoadingState {


    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_DATA_LOADING_STATE;
    }
}
