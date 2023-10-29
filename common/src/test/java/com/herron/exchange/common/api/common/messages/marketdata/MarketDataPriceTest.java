package com.herron.exchange.common.api.common.messages.marketdata;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.PriceType;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import com.herron.exchange.common.api.common.messages.marketdata.entries.ImmutableMarketDataPrice;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.ImmutableMarketDataPriceStaticKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MarketDataPriceTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = ImmutableMarketDataPrice.builder()
                .priceType(PriceType.SETTLEMENT)
                .staticKey(ImmutableMarketDataPriceStaticKey.builder().instrumentId("instrumentId").build())
                .timeComponentKey(ImmutableDefaultTimeComponentKey.builder().timeOfEvent(Timestamp.now()).build())
                .price(Price.create(1))
                .build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }
}