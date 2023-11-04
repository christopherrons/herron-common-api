package com.herron.exchange.common.api.common.messages.marketdata.entries;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.DayCountConventionEnum;
import com.herron.exchange.common.api.common.enums.InterpolationMethod;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import com.herron.exchange.common.api.common.messages.marketdata.ImmutableDefaultTimeComponentKey;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.ImmutableMarketDataYieldCurveStaticKey;
import com.herron.exchange.common.api.common.parametricmodels.yieldcurve.YieldCurve;
import com.herron.exchange.common.api.common.parametricmodels.yieldcurve.model.YieldCurveModelParameters;
import com.herron.exchange.common.api.common.parametricmodels.yieldcurve.model.YieldPoint;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MarketDataYieldCurveTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var yieldCurve = YieldCurve.create(
                "curveId",
                new YieldCurveModelParameters(DayCountConventionEnum.ACT360, InterpolationMethod.CUBIC_SPLINE, LocalDate.now(), LocalDate.now(), List.of(new YieldPoint(1, 2), new YieldPoint(2, 4), new YieldPoint(3, 8)
                ))
        );
        var object = ImmutableMarketDataYieldCurve.builder()
                .yieldCurve(yieldCurve)
                .staticKey(ImmutableMarketDataYieldCurveStaticKey.builder().curveId("curveId").build())
                .timeComponentKey(ImmutableDefaultTimeComponentKey.builder().timeOfEvent(Timestamp.now()).build())
                .build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }

}