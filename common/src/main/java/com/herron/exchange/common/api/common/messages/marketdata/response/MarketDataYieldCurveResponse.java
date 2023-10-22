package com.herron.exchange.common.api.common.messages.marketdata.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.MarketDataResponse;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.marketdata.entries.MarketDataYieldCurve;
import jakarta.annotation.Nullable;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.MARKET_DATA_YIELD_CURVE_RESPONSE;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMarketDataYieldCurveResponse.Builder.class)
@SuppressWarnings("immutables:from")
public interface MarketDataYieldCurveResponse extends MarketDataResponse {
    @Nullable
    MarketDataYieldCurve yieldCurveEntry();

    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return MARKET_DATA_YIELD_CURVE_RESPONSE;
    }
}
