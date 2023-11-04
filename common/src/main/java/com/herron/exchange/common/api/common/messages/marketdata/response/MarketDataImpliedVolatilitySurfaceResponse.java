package com.herron.exchange.common.api.common.messages.marketdata.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.MarketDataResponse;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.marketdata.entries.MarketDataImpliedVolatilitySurface;
import jakarta.annotation.Nullable;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.Status.ERROR;
import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.MARKET_DATA_IMPLIED_VOLATILITY_SURFACE_RESPONSE;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMarketDataImpliedVolatilitySurfaceResponse.Builder.class)
@SuppressWarnings("immutables:from")
public interface MarketDataImpliedVolatilitySurfaceResponse extends MarketDataResponse {

    static MarketDataImpliedVolatilitySurfaceResponse createErrorResponse(String errorMessage) {
        return ImmutableMarketDataImpliedVolatilitySurfaceResponse.builder()
                .status(ERROR)
                .error(errorMessage)
                .build();
    }

    @Nullable
    MarketDataImpliedVolatilitySurface impliedVolatilitySurfaceEntry();

    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return MARKET_DATA_IMPLIED_VOLATILITY_SURFACE_RESPONSE;
    }
}
