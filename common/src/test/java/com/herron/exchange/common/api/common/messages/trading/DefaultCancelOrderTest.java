package com.herron.exchange.common.api.common.messages.trading;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.OrderCancelOperationTypeEnum;
import com.herron.exchange.common.api.common.enums.OrderExecutionTypeEnum;
import com.herron.exchange.common.api.common.enums.OrderSideEnum;
import com.herron.exchange.common.api.common.enums.OrderTypeEnum;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.Participant;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.common.Volume;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultCancelOrderTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = ImmutableDefaultCancelOrder.builder()
                .cancelOperationType(OrderCancelOperationTypeEnum.FAK)
                .currentVolume(Volume.create(1))
                .initialVolume(Volume.create(1))
                .instrumentId("instrument")
                .orderId("orderid")
                .orderbookId("orderbookid")
                .orderExecutionType(OrderExecutionTypeEnum.FAK)
                .orderSide(OrderSideEnum.BID)
                .timeOfEventMs(1)
                .price(Price.create(1))
                .orderType(OrderTypeEnum.MARKET)
                .participant(new Participant("member", "user"))
                .build();

        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }
}