package com.herron.exchange.common.api.common.messages.refdata;

import com.herron.exchange.common.api.common.api.EquityInstrument;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

@Value.Immutable
public interface HerronEquityInstrument extends EquityInstrument {

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_STOCK_INSTRUMENT;
    }
}
