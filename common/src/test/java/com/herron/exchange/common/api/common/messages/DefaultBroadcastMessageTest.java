package com.herron.exchange.common.api.common.messages;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.DefaultBusinessCalendar;
import com.herron.exchange.common.api.common.messages.refdata.ImmutableDefaultMarket;
import com.herron.exchange.common.api.common.messages.refdata.ImmutableDefaultProduct;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultBroadcastMessageTest {

    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var market = ImmutableDefaultMarket.builder()
                .marketId("market")
                .businessCalendar(DefaultBusinessCalendar.defaultWeekendCalendar())
                .build();

        var product = ImmutableDefaultProduct.builder()
                .productId("product")
                .currency("eur")
                .market(market)
                .build();

        var object = ImmutableDefaultBroadcastMessage.builder()
                .sequenceNumber(1)
                .message(product)
                .timeOfEventMs(1)
                .build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }

}