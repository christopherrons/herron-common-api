package com.herron.exchange.common.api.common.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.herron.exchange.common.api.common.api.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HerronJsonMapperUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HerronJsonMapperUtil.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    public static String encodeMessage(Message message) {
        try {
            return OBJECT_MAPPER.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            LOGGER.warn("Unable to map message {}: {}", message, e);
        }
        return null;
    }

    public static Message decodeMessage(String message, Class<? extends Message> classToBeDecoded) {
        try {
            return OBJECT_MAPPER.readValue(message, classToBeDecoded);
        } catch (JsonProcessingException e) {
            LOGGER.warn("Unable to map message {}: {}", message, e);
        }
        return null;
    }

}
