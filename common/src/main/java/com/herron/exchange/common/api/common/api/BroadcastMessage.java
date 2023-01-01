package com.herron.exchange.common.api.common.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

public interface BroadcastMessage extends Message {

    long sequenceNumber();

    String serializedMessage();
    MessageTypesEnum serializedMessageType();

    @JsonIgnore
    Message message();

}
