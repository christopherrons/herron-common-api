package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.broadcasts.DataStreamState;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultDataStreamState.Builder.class)
public interface DefaultDataStreamState extends DataStreamState {


    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.DEFAULT_DATA_STREAM_STATE;
    }
}
