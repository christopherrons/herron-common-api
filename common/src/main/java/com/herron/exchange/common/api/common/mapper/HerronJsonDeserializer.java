package com.herron.exchange.common.api.common.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.herron.exchange.common.api.common.api.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HerronJsonDeserializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(HerronJsonDeserializer.class);
    private final Class<? extends Message> classToBeDecoded;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public HerronJsonDeserializer(Class<? extends Message> classToBeDecoded) {
        this.classToBeDecoded = classToBeDecoded;
    }

    public Message decodeMessage(final String message) {
        try {
            return objectMapper.readValue(message, classToBeDecoded);
        } catch (JsonProcessingException e) {
            LOGGER.warn("Unable to map message {}: {}", message, e);
        }
        return null;
    }

}
