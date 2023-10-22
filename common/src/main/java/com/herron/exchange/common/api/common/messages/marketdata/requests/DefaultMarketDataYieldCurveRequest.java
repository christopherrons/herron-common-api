package com.herron.exchange.common.api.common.messages.marketdata.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.requests.MarketDataYieldCurveRequest;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.DEFAULT_MARKET_DATA_YIELD_CURVE_REQUEST;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultMarketDataYieldCurveRequest.Builder.class)
public interface DefaultMarketDataYieldCurveRequest extends MarketDataYieldCurveRequest {
    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return DEFAULT_MARKET_DATA_YIELD_CURVE_REQUEST;
    }
}
