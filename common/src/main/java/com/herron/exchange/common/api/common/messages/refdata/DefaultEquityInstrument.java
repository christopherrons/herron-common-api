package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.instruments.EquityInstrument;
import com.herron.exchange.common.api.common.enums.messagetypes.ReferenceDataMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.ReferenceDataMessageTypeEnum.DEFAULT_EQUITY_INSTRUMENT;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultEquityInstrument.Builder.class)
public interface DefaultEquityInstrument extends EquityInstrument {

    @Value.Derived
    default ReferenceDataMessageTypeEnum messageType() {
        return DEFAULT_EQUITY_INSTRUMENT;
    }
}
