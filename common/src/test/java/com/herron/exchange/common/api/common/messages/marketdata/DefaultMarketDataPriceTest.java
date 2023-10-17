package com.herron.exchange.common.api.common.messages.marketdata;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.PriceType;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.Price;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultMarketDataPriceTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = ImmutableDefaultMarketDataPrice.builder()
                .priceType(PriceType.SETTLEMENT)
                .staticKey(ImmutableDefaultMarketDataPriceStaticKey.builder().instrumentId("instrumentId").build())
                .timeComponentKey(ImmutableDefaultTimeComponentKey.builder().timeOfEvent(LocalDateTime.now()).build())
                .price(Price.create(1))
                .build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }
}