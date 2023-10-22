package com.herron.exchange.common.api.common.messages.marketdata.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.response.MarketDataYieldCurveResponse;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.DEFAULT_MARKET_DATA_YIELD_CURVE_RESPONSE;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultMarketDataYieldCurveResponse.Builder.class)
public interface DefaultMarketDataYieldCurveResponse extends MarketDataYieldCurveResponse {
    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return DEFAULT_MARKET_DATA_YIELD_CURVE_RESPONSE;
    }
}
