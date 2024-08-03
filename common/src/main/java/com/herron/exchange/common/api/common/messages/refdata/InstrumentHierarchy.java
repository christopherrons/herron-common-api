package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.messagetypes.ReferenceDataMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import com.herron.exchange.common.api.common.messages.common.Tree;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.ReferenceDataMessageTypeEnum.INSTRUMENT_HIERARCHY;

@Value.Immutable
@JsonDeserialize(builder = ImmutableInstrumentHierarchy.Builder.class)
public interface InstrumentHierarchy extends Message {

    Timestamp timeStamp();

    Tree instrumentTree();

    @Value.Derived
    default ReferenceDataMessageTypeEnum messageType() {
        return INSTRUMENT_HIERARCHY;
    }
}
