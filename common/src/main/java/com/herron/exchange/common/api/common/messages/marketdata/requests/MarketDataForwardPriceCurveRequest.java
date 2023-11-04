package com.herron.exchange.common.api.common.messages.marketdata.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.MarketDataRequest;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.MarketDataForwardPriceCurveStaticKey;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.MARKET_DATA_FORWARD_PRICE_CURVE_REQUEST;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMarketDataForwardPriceCurveRequest.Builder.class)
@SuppressWarnings("immutables:from")
public interface MarketDataForwardPriceCurveRequest extends MarketDataRequest {

    @Override
    MarketDataForwardPriceCurveStaticKey staticKey();

    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return MARKET_DATA_FORWARD_PRICE_CURVE_REQUEST;
    }
}
