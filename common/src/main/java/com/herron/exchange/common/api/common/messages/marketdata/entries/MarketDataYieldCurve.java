package com.herron.exchange.common.api.common.messages.marketdata.entries;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.MarketDataEntry;
import com.herron.exchange.common.api.common.curves.YieldCurve;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.MarketDataYieldCurveStaticKey;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.MARKET_DATA_YIELD_CURVE;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMarketDataYieldCurve.Builder.class)
@SuppressWarnings("immutables:from")
public interface MarketDataYieldCurve extends MarketDataEntry {

    YieldCurve yieldCurve();

    @Override
    MarketDataYieldCurveStaticKey staticKey();

    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return MARKET_DATA_YIELD_CURVE;
    }

}
