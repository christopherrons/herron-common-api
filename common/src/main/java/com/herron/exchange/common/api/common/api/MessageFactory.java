package com.herron.exchange.common.api.common.api;

public interface MessageFactory {

    Message deserializeMessage(String value);

    <T> T deserializeMessage(Object message, Class<T> classToBeDecoded);

    String serialize(Object object);
}
