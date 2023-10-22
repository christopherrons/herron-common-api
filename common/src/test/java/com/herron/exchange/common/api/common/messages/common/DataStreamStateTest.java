package com.herron.exchange.common.api.common.messages.common;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.DataStreamEnum;
import com.herron.exchange.common.api.common.enums.EventType;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataStreamStateTest {

    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = ImmutableDataStreamState.builder().eventType(EventType.SYSTEM).state(DataStreamEnum.DONE).timeOfEventMs(1).build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }

}