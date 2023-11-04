package com.herron.exchange.common.api.common.messages.marketdata.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.MarketDataRequest;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.MarketDataImpliedVolatilitySurfaceStaticKey;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.MARKET_DATA_IMPLIED_VOLATILITY_SURFACE_REQUEST;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMarketDataImpliedVolatilitySurfaceRequest.Builder.class)
@SuppressWarnings("immutables:from")
public interface MarketDataImpliedVolatilitySurfaceRequest extends MarketDataRequest {

    @Override
    MarketDataImpliedVolatilitySurfaceStaticKey staticKey();

    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return MARKET_DATA_IMPLIED_VOLATILITY_SURFACE_REQUEST;
    }
}
