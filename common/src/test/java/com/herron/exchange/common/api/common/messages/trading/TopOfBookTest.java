package com.herron.exchange.common.api.common.messages.trading;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.EventType;
import com.herron.exchange.common.api.common.enums.QuoteTypeEnum;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TopOfBookTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var bidQuote = ImmutablePriceQuote.builder()
                .price(Price.create(1))
                .quoteType(QuoteTypeEnum.BID_PRICE)
                .orderbookId("orderbookid")
                .eventType(EventType.SYSTEM)
                .timeOfEvent(Timestamp.now())
                .build();
        var object = ImmutableTopOfBook.builder()
                .bidQuote(bidQuote)
                .orderbookId("orderbookid")
                .eventType(EventType.SYSTEM)
                .timeOfEvent(Timestamp.now())
                .build();

        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
        assertEquals(object, messageFactory.deserializeMessage(value));
    }
}