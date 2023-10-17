package com.herron.exchange.common.api.common.messages.marketdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.TimeComponentKey;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;


@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultTimeComponentKey.Builder.class)
public interface DefaultTimeComponentKey extends TimeComponentKey {

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.DEFAULT_TIME_COMPONENT_KEY;
    }
}
