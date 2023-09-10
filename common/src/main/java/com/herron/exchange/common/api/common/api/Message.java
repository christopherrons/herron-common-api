package com.herron.exchange.common.api.common.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

public interface Message {
    @JsonIgnore
    Message getCopy();

    MessageTypesEnum messageType();

    default Message deserialize(String message) {
        return messageType().deserializeMessage(message);
    }

    default String serialize() {
        return messageType().serializeMessage(this);
    }

}
