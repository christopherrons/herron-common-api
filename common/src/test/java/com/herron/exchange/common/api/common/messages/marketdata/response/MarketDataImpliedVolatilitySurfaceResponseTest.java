package com.herron.exchange.common.api.common.messages.marketdata.response;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.Status;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import com.herron.exchange.common.api.common.messages.marketdata.ImmutableDefaultTimeComponentKey;
import com.herron.exchange.common.api.common.messages.marketdata.entries.ImmutableMarketDataImpliedVolatilitySurface;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.ImmutableMarketDataImpliedVolatilitySurfaceStaticKey;
import com.herron.exchange.common.api.common.parametricmodels.impliedvolsurface.ImpliedVolatilitySurface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MarketDataImpliedVolatilitySurfaceResponseTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var surface = ImpliedVolatilitySurface.create("id", 10);
        var value1 = surface.getImpliedVolatility(0, 0);
        var object = ImmutableMarketDataImpliedVolatilitySurfaceResponse.builder()
                .impliedVolatilitySurfaceEntry(ImmutableMarketDataImpliedVolatilitySurface.builder()
                        .timeComponentKey(ImmutableDefaultTimeComponentKey.builder().timeOfEvent(Timestamp.now()).build())
                        .staticKey(ImmutableMarketDataImpliedVolatilitySurfaceStaticKey.builder().instrumentId("instrumentId").build())
                        .impliedVolatilitySurface(surface)
                        .build())
                .status(Status.OK)
                .build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
        var value2 = messageFactory.deserializeMessage(value, MarketDataImpliedVolatilitySurfaceResponse.class).impliedVolatilitySurfaceEntry().impliedVolatilitySurface().getImpliedVolatility(0, 0);
        assertEquals(value1, value2);
    }
}