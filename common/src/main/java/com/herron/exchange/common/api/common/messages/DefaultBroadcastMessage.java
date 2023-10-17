package com.herron.exchange.common.api.common.messages;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.herron.exchange.common.api.common.api.broadcasts.BroadcastMessage;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableDefaultBroadcastMessage.class)
@JsonDeserialize(builder = ImmutableDefaultBroadcastMessage.Builder.class)
public interface DefaultBroadcastMessage extends BroadcastMessage {

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.DEFAULT_BROADCAST_MESSAGE;
    }
}
