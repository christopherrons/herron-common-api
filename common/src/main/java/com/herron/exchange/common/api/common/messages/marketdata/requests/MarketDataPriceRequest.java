package com.herron.exchange.common.api.common.messages.marketdata.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.MarketDataRequest;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.MarketDataPriceStaticKey;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.MARKET_DATA_PRICE_REQUEST;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMarketDataPriceRequest.Builder.class)
@SuppressWarnings("immutables:from")
public interface MarketDataPriceRequest extends MarketDataRequest {

    @Override
    MarketDataPriceStaticKey staticKey();

    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return MARKET_DATA_PRICE_REQUEST;
    }
}
