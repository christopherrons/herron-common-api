package com.herron.exchange.common.api.common.messages.trading;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.Participant;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.common.Volume;
import org.junit.jupiter.api.Test;

import static com.herron.exchange.common.api.common.enums.OrderSideEnum.BID;
import static com.herron.exchange.common.api.common.enums.OrderTypeEnum.MARKET;
import static com.herron.exchange.common.api.common.enums.OrderUpdatedOperationTypeEnum.EXTERNAL_UPDATE;
import static com.herron.exchange.common.api.common.enums.TimeInForceEnum.FAK;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultUpdateOrderTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = ImmutableDefaultUpdateOrder.builder()
                .updateOperationType(EXTERNAL_UPDATE)
                .currentVolume(Volume.create(1))
                .initialVolume(Volume.create(1))
                .instrumentId("instrument")
                .orderId("orderid")
                .orderbookId("orderbookid")
                .timeInForce(FAK)
                .orderSide(BID)
                .timeOfEventMs(1)
                .price(Price.create(1))
                .orderType(MARKET)
                .participant(new Participant("member", "user"))
                .build();

        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }
}