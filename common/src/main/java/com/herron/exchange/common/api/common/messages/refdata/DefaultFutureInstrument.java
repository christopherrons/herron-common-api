package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.instruments.FutureInstrument;
import com.herron.exchange.common.api.common.enums.messagetypes.ReferenceDataMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.ReferenceDataMessageTypeEnum.DEFAULT_FUTURE_INSTRUMENT;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultFutureInstrument.Builder.class)
public interface DefaultFutureInstrument extends FutureInstrument {

    @Value.Derived
    default ReferenceDataMessageTypeEnum messageType() {
        return DEFAULT_FUTURE_INSTRUMENT;
    }
}
