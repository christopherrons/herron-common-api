package com.herron.exchange.common.api.common.messages.marketdata.entries;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.entries.MarketDataYieldCurve;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.DEFAULT_MARKET_DATA_YIELD_CURVE;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultMarketDataYieldCurve.Builder.class)
@SuppressWarnings("immutables:from")
public interface DefaultMarketDataYieldCurve extends MarketDataYieldCurve {

    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return DEFAULT_MARKET_DATA_YIELD_CURVE;
    }

}
