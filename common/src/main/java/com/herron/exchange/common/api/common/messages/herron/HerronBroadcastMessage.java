package com.herron.exchange.common.api.common.messages.herron;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.BroadcastMessage;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.mapper.HerronBroadCastJsonDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = HerronBroadCastJsonDeserializer.class)
public record HerronBroadcastMessage(Message message,
                                     String messageMessageType,
                                     long sequenceNumber,
                                     long timeStampInMs) implements BroadcastMessage {

    public HerronBroadcastMessage(HerronBroadcastMessage broadcastMessage) {
        this(broadcastMessage.message(),
                broadcastMessage.messageMessageType(),
                broadcastMessage.sequenceNumber(),
                broadcastMessage.timeStampInMs());
    }

    @Override
    public Message getCopy() {
        return new HerronBroadcastMessage(this);
    }


    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_BROADCAST_MESSAGE;
    }
}
