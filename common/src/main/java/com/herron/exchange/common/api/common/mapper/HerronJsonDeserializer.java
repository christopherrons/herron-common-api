package com.herron.exchange.common.api.common.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.herron.exchange.common.api.common.api.Message;

public class HerronJsonDeserializer {
    private final Class<? extends Message> classToBeDecoded;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public HerronJsonDeserializer(Class<? extends Message> classToBeDecoded) {
        this.classToBeDecoded = classToBeDecoded;
    }

    public Message decodeMessage(final String message) {
        try {
            return objectMapper.readValue(message, classToBeDecoded);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
