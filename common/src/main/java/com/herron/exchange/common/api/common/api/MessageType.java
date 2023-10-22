package com.herron.exchange.common.api.common.api;


public interface MessageType {
    String getMessageTypeId();

    Class<? extends Message> getClassToBeDeserialized();

}
