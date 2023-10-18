package com.herron.exchange.common.api.common.messages.trading;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.Participant;
import com.herron.exchange.common.api.common.messages.common.Volume;
import org.junit.jupiter.api.Test;

import static com.herron.exchange.common.api.common.enums.OrderOperationCauseEnum.FILLED;
import static com.herron.exchange.common.api.common.enums.OrderOperationEnum.INSERT;
import static com.herron.exchange.common.api.common.enums.OrderSideEnum.BID;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultMarketOrderTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = ImmutableDefaultMarketOrder.builder()
                .orderOperationCause(FILLED)
                .orderOperation(INSERT)
                .currentVolume(Volume.create(1))
                .initialVolume(Volume.create(1))
                .instrumentId("instrument")
                .orderId("orderid")
                .orderbookId("orderbookid")
                .orderSide(BID)
                .timeOfEventMs(1)
                .participant(new Participant("member", "user"))
                .build();

        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }
}