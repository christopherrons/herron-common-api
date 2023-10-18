package com.herron.exchange.common.api.common.messages.marketdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.TimeComponentKey;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.MessageTypesEnum.DEFAULT_TIME_COMPONENT_KEY;


@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultTimeComponentKey.Builder.class)
public interface DefaultTimeComponentKey extends TimeComponentKey {

    @Value.Derived
    default MessageTypesEnum messageType() {
        return DEFAULT_TIME_COMPONENT_KEY;
    }
}
