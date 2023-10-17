package com.herron.exchange.common.api.common.messages.trading;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.StateChangeTypeEnum;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultStateChangeTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = ImmutableDefaultStateChange.builder()
                .stateChangeType(StateChangeTypeEnum.AUCTION_RUN)
                .timeOfEventMs(1)
                .orderbookId("obid")
                .build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }
}