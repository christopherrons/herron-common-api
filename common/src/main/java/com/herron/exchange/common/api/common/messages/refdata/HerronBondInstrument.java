package com.herron.exchange.common.api.common.messages.refdata;

import com.herron.exchange.common.api.common.api.BondInstrument;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

@Value.Immutable
public interface HerronBondInstrument extends BondInstrument {

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_BOND_INSTRUMENT;
    }
}
