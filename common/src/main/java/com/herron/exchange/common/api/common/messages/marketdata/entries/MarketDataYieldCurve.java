package com.herron.exchange.common.api.common.messages.marketdata.entries;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.MarketDataEntry;
import com.herron.exchange.common.api.common.curves.YieldCurve;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import com.herron.exchange.common.api.common.messages.marketdata.ImmutableDefaultTimeComponentKey;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.ImmutableMarketDataYieldCurveStaticKey;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.MarketDataYieldCurveStaticKey;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.MARKET_DATA_YIELD_CURVE;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMarketDataYieldCurve.Builder.class)
@SuppressWarnings("immutables:from")
public interface MarketDataYieldCurve extends MarketDataEntry {

    static MarketDataYieldCurve create(Timestamp timeStamp, String id, YieldCurve curve) {
        return ImmutableMarketDataYieldCurve.builder()
                .timeComponentKey(ImmutableDefaultTimeComponentKey.builder().timeOfEvent(timeStamp).build())
                .staticKey(ImmutableMarketDataYieldCurveStaticKey.builder().curveId(id).build())
                .yieldCurve(curve)
                .build();
    }

    YieldCurve yieldCurve();

    @Override
    MarketDataYieldCurveStaticKey staticKey();

    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return MARKET_DATA_YIELD_CURVE;
    }

    @Value.Check
    default void checkCurveId() {
        if (!yieldCurve().getId().equals(staticKey().curveId())) {
            throw new IllegalArgumentException(String.format("Curve id %s does not match static key id %s", yieldCurve().getId(), staticKey().curveId()));
        }
    }

}
