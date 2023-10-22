package com.herron.exchange.common.api.common.messages.marketdata.statickeys;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.statickeys.MarketDataYieldCurveStaticKey;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.DEFAULT_MARKET_DATA_YIELD_CURVE_STATIC_KEY;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultMarketDataYieldCurveStaticKey.Builder.class)
public interface DefaultMarketDataYieldCurveStaticKey extends MarketDataYieldCurveStaticKey {

    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return DEFAULT_MARKET_DATA_YIELD_CURVE_STATIC_KEY;
    }
}
