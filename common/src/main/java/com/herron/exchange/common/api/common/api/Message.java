package com.herron.exchange.common.api.common.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "@type"
)
public interface Message {
    @JsonProperty("@type")
    default String messageTypeId() {
        return messageType().getMessageTypeId();
    }

    @JsonIgnore
    MessageType messageType();

}
