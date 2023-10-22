package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.instruments.OptionInstrument;
import com.herron.exchange.common.api.common.enums.messagetypes.ReferenceDataMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.ReferenceDataMessageTypeEnum.DEFAULT_OPTION_INSTRUMENT;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultOptionInstrument.Builder.class)
public interface DefaultOptionInstrument extends OptionInstrument {

    @Value.Derived
    default ReferenceDataMessageTypeEnum messageType() {
        return DEFAULT_OPTION_INSTRUMENT;
    }
}
