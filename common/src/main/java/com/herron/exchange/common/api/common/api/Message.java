package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

public interface Message {

    MessageTypesEnum messageType();

    default Message deserialize(String message) {
        return messageType().deserializeMessage(message);
    }

    default String serialize() {
        return messageType().serializeMessage(this);
    }

}
