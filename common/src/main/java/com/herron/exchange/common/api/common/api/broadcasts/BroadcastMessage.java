package com.herron.exchange.common.api.common.api.broadcasts;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.herron.exchange.common.api.common.api.Event;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.messages.DefaultBroadcastMessage;
import org.immutables.value.Value;

@JsonSubTypes({
        @JsonSubTypes.Type(value = DefaultBroadcastMessage.class, name = "DEBM"),
})
public interface BroadcastMessage extends Event {

    long sequenceNumber();

    @Value.Derived
    default String messageMessageType() {
        return message().messageTypeId();
    }

    Message message();

}
