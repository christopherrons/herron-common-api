package com.herron.exchange.common.api.common.messages.marketdata.entries;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import com.herron.exchange.common.api.common.messages.marketdata.ImmutableDefaultTimeComponentKey;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.ImmutableMarketDataImpliedVolatilitySurfaceStaticKey;
import com.herron.exchange.common.api.common.parametricmodels.impliedvolsurface.ImpliedVolatilitySurface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MarketDataImpliedVolatilitySurfaceTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var surface = ImpliedVolatilitySurface.create(
                "surfaceId",
                10
        );
        var object = ImmutableMarketDataImpliedVolatilitySurface.builder()
                .impliedVolatilitySurface(surface)
                .staticKey(ImmutableMarketDataImpliedVolatilitySurfaceStaticKey.builder().instrumentId("surfaceId").build())
                .timeComponentKey(ImmutableDefaultTimeComponentKey.builder().timeOfEvent(Timestamp.now()).build())
                .build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }
}