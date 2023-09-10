package com.herron.exchange.common.api.common.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.mapper.HerronBroadCastJsonDeserializer;

@JsonDeserialize(using = HerronBroadCastJsonDeserializer.class)
public interface BroadcastMessage extends Event {

    long sequenceNumber();

    String messageMessageType();

    Message message();

}
