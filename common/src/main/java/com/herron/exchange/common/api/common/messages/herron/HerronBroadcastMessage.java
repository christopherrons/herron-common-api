package com.herron.exchange.common.api.common.messages.herron;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.BroadcastMessage;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronBroadcastMessage(String serializedMessage,
                                     MessageTypesEnum serializedMessageType,
                                     long sequenceNumber,
                                     long timeStampInMs) implements BroadcastMessage {

    public HerronBroadcastMessage(BroadcastMessage broadcastMessage) {
        this(broadcastMessage.serializedMessage(),
                broadcastMessage.serializedMessageType(),
                broadcastMessage.sequenceNumber(),
                broadcastMessage.timeStampInMs());
    }

    @Override
    public Message getCopy() {
        return new HerronBroadcastMessage(this);
    }

    @Override
    public Message message() {
        return serializedMessageType.deserializeMessage(serializedMessage);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_BROADCAST_MESSAGE;
    }
}
