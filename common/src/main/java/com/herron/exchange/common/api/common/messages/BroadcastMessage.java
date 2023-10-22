package com.herron.exchange.common.api.common.messages;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.herron.exchange.common.api.common.api.Event;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.messagetypes.CommonMessageTypesEnum;
import com.herron.exchange.common.api.common.messages.common.PartitionKey;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.CommonMessageTypesEnum.BROADCAST_MESSAGE;

@Value.Immutable
@JsonSerialize(as = ImmutableBroadcastMessage.class)
@JsonDeserialize(builder = ImmutableBroadcastMessage.Builder.class)
public interface BroadcastMessage extends Event {
    long sequenceNumber();

    Message message();

    PartitionKey partitionKey();

    @Value.Derived
    default CommonMessageTypesEnum messageType() {
        return BROADCAST_MESSAGE;
    }
}
