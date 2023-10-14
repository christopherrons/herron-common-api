package com.herron.exchange.common.api.common.messages;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.broadcasts.BroadcastMessage;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableHerronBroadcastMessage.Builder.class)
public interface HerronBroadcastMessage extends BroadcastMessage {

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_BROADCAST_MESSAGE;
    }
}
