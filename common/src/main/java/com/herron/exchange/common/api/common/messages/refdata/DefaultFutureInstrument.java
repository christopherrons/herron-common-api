package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.instruments.FutureInstrument;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.MessageTypesEnum.DEFAULT_FUTURE_INSTRUMENT;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultFutureInstrument.Builder.class)
public interface DefaultFutureInstrument extends FutureInstrument {

    @Value.Derived
    default MessageTypesEnum messageType() {
        return DEFAULT_FUTURE_INSTRUMENT;
    }
}
