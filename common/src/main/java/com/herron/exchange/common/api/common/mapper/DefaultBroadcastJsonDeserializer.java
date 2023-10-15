package com.herron.exchange.common.api.common.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.messages.ImmutableDefaultBroadcastMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DefaultBroadcastJsonDeserializer extends StdDeserializer<Message> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultBroadcastJsonDeserializer.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DefaultBroadcastJsonDeserializer() {
        this(null);
    }

    public DefaultBroadcastJsonDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Message deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec oc = p.getCodec();
        JsonNode node = oc.readTree(p);

        String messageTypeString = node.get("messageMessageType").asText();
        String sequenceNumber = node.get("sequenceNumber").asText();
        String timeOfEventMs = node.get("timeOfEventMs").asText();
        var messageType = MessageTypesEnum.getMessageTypeEnum(messageTypeString);
        return ImmutableDefaultBroadcastMessage.builder()
                .timeOfEventMs(Long.parseLong(timeOfEventMs))
                .message(messageType.deserializeMessage(node.get("message")))
                .sequenceNumber(Long.parseLong(sequenceNumber))
                .build();
    }
}
