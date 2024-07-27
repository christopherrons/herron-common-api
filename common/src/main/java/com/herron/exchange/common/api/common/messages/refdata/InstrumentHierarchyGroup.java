package com.herron.exchange.common.api.common.messages.refdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.messagetypes.ReferenceDataMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import jakarta.annotation.Nullable;
import org.immutables.value.Value;

import java.util.Set;

import static com.herron.exchange.common.api.common.enums.messagetypes.ReferenceDataMessageTypeEnum.INSTRUMENT_HIERARCHY_GROUP;

@Value.Immutable
@JsonDeserialize(builder = ImmutableInstrumentHierarchyGroup.Builder.class)
public interface InstrumentHierarchyGroup extends Message {

    Timestamp timeStamp();

    String groupId();

    @Nullable
    Set<InstrumentHierarchyGroup> parentGroup();

    @Nullable
    Set<InstrumentHierarchyGroup> childGroup();

    @Nullable
    Set<String> instrumentIds();

    @Value.Derived
    default ReferenceDataMessageTypeEnum messageType() {
        return INSTRUMENT_HIERARCHY_GROUP;
    }
}
