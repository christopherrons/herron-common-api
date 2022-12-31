package com.herron.exchange.common.api.common.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.mapper.HerronJsonMapperUtil;

public interface Message {
    @JsonIgnore
    Message getCopy();

    MessageTypesEnum messageType();

    long timeStampInMs();

    default Message deserialize(String message) {
        return HerronJsonMapperUtil.decodeMessage(message, this.getClass());
    }

    default String serialize() {
        return HerronJsonMapperUtil.encodeMessage(this);
    }

}
