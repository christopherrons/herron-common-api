package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.instruments.BondInstrument;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.MessageTypesEnum.DEFAULT_BOND_INSTRUMENT;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultBondInstrument.Builder.class)
public interface DefaultBondInstrument extends BondInstrument {

    @Value.Derived
    default MessageTypesEnum messageType() {
        return DEFAULT_BOND_INSTRUMENT;
    }
}
