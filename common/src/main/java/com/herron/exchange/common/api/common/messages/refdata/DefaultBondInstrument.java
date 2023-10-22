package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.instruments.BondInstrument;
import com.herron.exchange.common.api.common.enums.messagetypes.ReferenceDataMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.ReferenceDataMessageTypeEnum.DEFAULT_BOND_INSTRUMENT;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultBondInstrument.Builder.class)
@SuppressWarnings("immutables:from")
public interface DefaultBondInstrument extends BondInstrument {

    @Value.Derived
    default ReferenceDataMessageTypeEnum messageType() {
        return DEFAULT_BOND_INSTRUMENT;
    }
}
