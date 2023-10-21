package com.herron.exchange.common.api.common.messages.trading;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.Price;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static com.herron.exchange.common.api.common.enums.OrderSideEnum.BID;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultPriceQuoteTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = ImmutableDefaultPriceQuote.builder()
                .price(Price.create(1))
                .side(BID)
                .orderbookId("orderbookid")
                .timeOfEventMs(Instant.now().toEpochMilli())
                .build();

        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }
}