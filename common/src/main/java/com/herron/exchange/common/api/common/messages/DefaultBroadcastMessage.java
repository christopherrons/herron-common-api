package com.herron.exchange.common.api.common.messages;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.herron.exchange.common.api.common.api.broadcasts.BroadcastMessage;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.MessageTypesEnum.DEFAULT_BROADCAST_MESSAGE;

@Value.Immutable
@JsonSerialize(as = ImmutableDefaultBroadcastMessage.class)
@JsonDeserialize(builder = ImmutableDefaultBroadcastMessage.Builder.class)
public interface DefaultBroadcastMessage extends BroadcastMessage {

    @Value.Derived
    default MessageTypesEnum messageType() {
        return DEFAULT_BROADCAST_MESSAGE;
    }
}
