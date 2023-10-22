package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.broadcasts.DataStreamState;
import com.herron.exchange.common.api.common.enums.messagetypes.CommonMessageTypesEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.CommonMessageTypesEnum.DEFAULT_DATA_STREAM_STATE;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultDataStreamState.Builder.class)
public interface DefaultDataStreamState extends DataStreamState {

    @Value.Derived
    default CommonMessageTypesEnum messageType() {
        return DEFAULT_DATA_STREAM_STATE;
    }
}
