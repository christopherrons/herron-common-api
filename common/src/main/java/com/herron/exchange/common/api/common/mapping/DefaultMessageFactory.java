package com.herron.exchange.common.api.common.mapping;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.messagetypes.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.Map;


public class DefaultMessageFactory implements MessageFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMessageFactory.class);
    private final SimpleModule typeIdModule = new SimpleModule("DEFAULT-TYPE-ID");
    private final ObjectMapper objectMapper;

    public DefaultMessageFactory() {
        this(Map.of());
    }

    public DefaultMessageFactory(Map<String, Class<? extends Message>> idToClassImplementation) {
        registerMessageTypes(idToClassImplementation);
        this.objectMapper = configure(Jackson2ObjectMapperBuilder.json()).build();
    }

    private void registerMessageTypes(Map<String, Class<? extends Message>> idToClassImplementation) {
        CommonMessageTypesEnum.getIdToClassImplementation().forEach(this::registerSubtype);
        MarketDataMessageTypeEnum.getIdToClassImplementation().forEach(this::registerSubtype);
        PricingMessageTypeEnum.getIdToClassImplementation().forEach(this::registerSubtype);
        ReferenceDataMessageTypeEnum.getIdToClassImplementation().forEach(this::registerSubtype);
        TradingMessageTypeEnum.getIdToClassImplementation().forEach(this::registerSubtype);
        idToClassImplementation.forEach(this::registerSubtype);
    }

    private void registerSubtype(String typeId, Class<? extends Message> implementationClazz) {
        typeIdModule.registerSubtypes(new NamedType(implementationClazz, typeId));
    }

    public Jackson2ObjectMapperBuilder configure(Jackson2ObjectMapperBuilder builder) {
        return builder
                // .featuresToEnable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .serializationInclusion(JsonInclude.Include.NON_EMPTY)
                .annotationIntrospector(new JacksonAnnotationIntrospector())
                .modules(
                        new Jdk8Module(),
                        new JavaTimeModule(),
                        typeIdModule
                );
    }

    @Override
    public String serialize(Object message) {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            LOGGER.warn("Unable to map message {}: {}", message, e);
        }
        return null;
    }

    @Override
    public Message deserializeMessage(String value) {
        return deserializeMessage(value, Message.class);
    }

    @Override
    public <T> T deserializeMessage(Object message, Class<T> classToBeDecoded) {
        try {
            if (message instanceof String messageString) {
                return objectMapper.readValue(messageString, classToBeDecoded);
            } else if (message instanceof JsonNode node) {
                return objectMapper.treeToValue(node, classToBeDecoded);
            }
            LOGGER.warn("Unable to map unhandled type: {}:", message);
        } catch (JsonProcessingException e) {
            LOGGER.warn("Unable to map message {}: {}", message, e);
        }
        return null;
    }
}
