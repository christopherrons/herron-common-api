package com.herron.exchange.common.api.common.messages.trading;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.EventType;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.Participant;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import com.herron.exchange.common.api.common.messages.common.Volume;
import org.junit.jupiter.api.Test;

import static com.herron.exchange.common.api.common.enums.OrderOperationCauseEnum.FILLED;
import static com.herron.exchange.common.api.common.enums.OrderOperationEnum.INSERT;
import static com.herron.exchange.common.api.common.enums.OrderSideEnum.BID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MarketOrderTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = ImmutableMarketOrder.builder()
                .orderOperationCause(FILLED)
                .orderOperation(INSERT)
                .currentVolume(Volume.create(1))
                .initialVolume(Volume.create(1))
                .instrumentId("instrument")
                .orderId("orderid")
                .orderbookId("orderbookid")
                .orderSide(BID)
                .timeOfEvent(Timestamp.from(1))
                .eventType(EventType.SYSTEM)
                .participant(new Participant("member", "user"))
                .build();

        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
        assertEquals(object, messageFactory.deserializeMessage(value));
    }
}