package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.Event;
import com.herron.exchange.common.api.common.enums.DataStreamEnum;
import com.herron.exchange.common.api.common.enums.messagetypes.CommonMessageTypesEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.CommonMessageTypesEnum.DATA_STREAM_STATE;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDataStreamState.Builder.class)
public interface DataStreamState extends Event {
    DataStreamEnum state();

    @Value.Derived
    default CommonMessageTypesEnum messageType() {
        return DATA_STREAM_STATE;
    }
}
