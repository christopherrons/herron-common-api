package com.herron.exchange.common.api.common.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.herron.exchange.common.api.common.api.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HerronJsonMapperUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HerronJsonMapperUtil.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String serializeMessage(Message message) {
        try {
            return OBJECT_MAPPER.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            LOGGER.warn("Unable to map message {}: {}", message, e);
        }
        return null;
    }

    public static Message deserializeMessage(Object message, Class<? extends Message> classToBeDecoded) {
        try {
            if (message instanceof String messageString) {
                return OBJECT_MAPPER.readValue(messageString, classToBeDecoded);
            } else if (message instanceof JsonNode node) {
                return OBJECT_MAPPER.treeToValue(node, classToBeDecoded);
            }
            LOGGER.warn("Unable to map unhandled type: {}:", message);
        } catch (JsonProcessingException e) {
            LOGGER.warn("Unable to map message {}: {}", message, e);
        }
        return null;
    }

}
