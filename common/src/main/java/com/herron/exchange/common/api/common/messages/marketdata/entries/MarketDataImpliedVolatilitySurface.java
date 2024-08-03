package com.herron.exchange.common.api.common.messages.marketdata.entries;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.MarketDataEntry;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import com.herron.exchange.common.api.common.math.parametricmodels.impliedvolsurface.ImpliedVolatilitySurface;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import com.herron.exchange.common.api.common.messages.marketdata.ImmutableDefaultTimeComponentKey;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.ImmutableMarketDataImpliedVolatilitySurfaceStaticKey;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.MarketDataImpliedVolatilitySurfaceStaticKey;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.MARKET_DATA_IMPLIED_VOLATILITY_SURFACE;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMarketDataImpliedVolatilitySurface.Builder.class)
@SuppressWarnings("immutables:from")
public interface MarketDataImpliedVolatilitySurface extends MarketDataEntry {

    static MarketDataImpliedVolatilitySurface create(Timestamp timestamp,
                                                     String instrumentId,
                                                     ImpliedVolatilitySurface impliedVolatilitySurface) {
        return ImmutableMarketDataImpliedVolatilitySurface.builder()
                .timeComponentKey(ImmutableDefaultTimeComponentKey.builder().timeOfEvent(timestamp).build())
                .staticKey(ImmutableMarketDataImpliedVolatilitySurfaceStaticKey.builder().instrumentId(instrumentId).build())
                .impliedVolatilitySurface(impliedVolatilitySurface)
                .build();
    }

    ImpliedVolatilitySurface impliedVolatilitySurface();

    @Override
    MarketDataImpliedVolatilitySurfaceStaticKey staticKey();

    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return MARKET_DATA_IMPLIED_VOLATILITY_SURFACE;
    }
}
