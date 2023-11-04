package com.herron.exchange.common.api.common.messages.marketdata.statickeys;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.StaticKey;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.MARKET_DATA_IMPLIED_VOLATILITY_SURFACE_KEY;
import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.MARKET_DATA_YIELD_CURVE_STATIC_KEY;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMarketDataImpliedVolatilitySurfaceStaticKey.Builder.class)
public interface MarketDataImpliedVolatilitySurfaceStaticKey extends StaticKey {

    String instrumentId();

    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return MARKET_DATA_IMPLIED_VOLATILITY_SURFACE_KEY;
    }
}
