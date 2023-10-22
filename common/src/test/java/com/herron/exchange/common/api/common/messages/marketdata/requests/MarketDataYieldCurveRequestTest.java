package com.herron.exchange.common.api.common.messages.marketdata.requests;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.MarketDataRequestTimeFilter;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.marketdata.ImmutableDefaultTimeComponentKey;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.ImmutableMarketDataYieldCurveStaticKey;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MarketDataYieldCurveRequestTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = ImmutableMarketDataYieldCurveRequest.builder()
                .staticKey(ImmutableMarketDataYieldCurveStaticKey.builder().curveId("curveId").build())
                .timeComponentKey(ImmutableDefaultTimeComponentKey.builder().timeOfEvent(LocalDateTime.now()).build())
                .timeFilter(MarketDataRequestTimeFilter.MATCH_TIME)
                .build();

        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }
}