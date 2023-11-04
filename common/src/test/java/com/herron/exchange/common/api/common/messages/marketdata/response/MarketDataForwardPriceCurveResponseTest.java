package com.herron.exchange.common.api.common.messages.marketdata.response;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.InterpolationMethod;
import com.herron.exchange.common.api.common.enums.Status;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.PureNumber;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import com.herron.exchange.common.api.common.messages.marketdata.ImmutableDefaultTimeComponentKey;
import com.herron.exchange.common.api.common.messages.marketdata.entries.ImmutableMarketDataForwardPriceCurve;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.ImmutableMarketDataForwardPriceCurveStaticKey;
import com.herron.exchange.common.api.common.parametricmodels.forwardcurve.ForwardPriceCurve;
import com.herron.exchange.common.api.common.parametricmodels.forwardcurve.model.ForwardCurveModelParameters;
import com.herron.exchange.common.api.common.parametricmodels.forwardcurve.model.ForwardPricePoint;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MarketDataForwardPriceCurveResponseTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var forwardPriceCurve = ForwardPriceCurve.create(
                "curveId",
                new ForwardCurveModelParameters(List.of(
                        new ForwardPricePoint(1, 2),
                        new ForwardPricePoint(2, 4),
                        new ForwardPricePoint(3, 8)
                ),
                        InterpolationMethod.CUBIC_SPLINE
                )
        );
        var value1 = forwardPriceCurve.getForwardPrice(1);
        var object = ImmutableMarketDataForwardPriceCurveResponse.builder()
                .status(Status.OK)
                .forwardPriceCurveEntry(ImmutableMarketDataForwardPriceCurve.builder()
                        .forwardPriceCurve(forwardPriceCurve)
                        .staticKey(ImmutableMarketDataForwardPriceCurveStaticKey.builder().instrumentId("curveId").strikePrice(PureNumber.ZERO).build())
                        .timeComponentKey(ImmutableDefaultTimeComponentKey.builder().timeOfEvent(Timestamp.now()).build())
                        .build())
                .build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
        var value2 = messageFactory.deserializeMessage(value, MarketDataForwardPriceCurveResponse.class).forwardPriceCurveEntry().forwardPriceCurve().getForwardPrice(1);
        assertEquals(value1, value2);
    }
}