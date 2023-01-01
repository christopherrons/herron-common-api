package com.herron.exchange.common.api.common.messages.bitstamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.api.BroadcastMessage;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BitstampBroadcastMessage(@JsonProperty("serializedMessage") String serializedMessage,
                                       @JsonProperty("serializedMessageTypeString") String serializedMessageTypeString,
                                       @JsonProperty("sequenceNumber") long sequenceNumber,
                                       @JsonProperty("timeStampInMs") long timeStampInMs) implements BroadcastMessage {

    public BitstampBroadcastMessage(BitstampBroadcastMessage broadcastMessage) {
        this(broadcastMessage.serializedMessage(),
                broadcastMessage.serializedMessageTypeString(),
                broadcastMessage.sequenceNumber(),
                broadcastMessage.timeStampInMs());
    }

    @Override
    public Message getCopy() {
        return new BitstampBroadcastMessage(this);
    }

    @Override
    public MessageTypesEnum serializedMessageType() {
        return MessageTypesEnum.getMessageTypeEnum(serializedMessageTypeString);
    }

    @Override
    public Message message() {
        return serializedMessageType().deserializeMessage(serializedMessage);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_BROADCAST_MESSAGE;
    }
}
