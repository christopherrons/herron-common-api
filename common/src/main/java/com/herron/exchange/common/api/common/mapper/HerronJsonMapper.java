package com.herron.exchange.common.api.common.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.herron.exchange.common.api.common.api.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HerronJsonMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(HerronJsonMapper.class);
    private final Class<? extends Message> classToBeDecoded;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public HerronJsonMapper(Class<? extends Message> classToBeDecoded) {
        this.classToBeDecoded = classToBeDecoded;
    }

    public String serializeMessage(Message message) {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            LOGGER.warn("Unable to map message {}: {}", message, e);
        }
        return null;
    }

    public Message deserializeMessage(String message) {
        try {
            return objectMapper.readValue(message, classToBeDecoded);
        } catch (JsonProcessingException e) {
            LOGGER.warn("Unable to map message {}: {}", message, e);
        }
        return null;
    }

}
