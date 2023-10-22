package com.herron.exchange.common.api.common.messages.marketdata;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.ImmutableDefaultMarketDataYieldCurveStaticKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultMarketDataYieldCurveStaticKeyTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = ImmutableDefaultMarketDataYieldCurveStaticKey.builder()
                .curveId("curveId")
                .build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }
}