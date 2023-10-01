package com.herron.exchange.common.api.common.messages.refdata;

import com.herron.exchange.common.api.common.api.OptionInstrument;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

@Value.Immutable
public interface HerronOptionInstrument extends OptionInstrument {

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_OPTION_INSTRUMENT;
    }
}
