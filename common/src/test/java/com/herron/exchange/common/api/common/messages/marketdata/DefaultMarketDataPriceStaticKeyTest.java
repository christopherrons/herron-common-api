package com.herron.exchange.common.api.common.messages.marketdata;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.ImmutableDefaultMarketDataPriceStaticKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultMarketDataPriceStaticKeyTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = ImmutableDefaultMarketDataPriceStaticKey.builder()
                .instrumentId("instrumentId")
                .build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }
}