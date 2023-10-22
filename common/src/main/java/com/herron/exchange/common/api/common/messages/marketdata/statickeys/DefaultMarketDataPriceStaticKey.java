package com.herron.exchange.common.api.common.messages.marketdata.statickeys;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.statickeys.MarketDataPriceStaticKey;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.DEFAULT_MARKET_DATA_PRICE_STATIC_KEY;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultMarketDataPriceStaticKey.Builder.class)
public interface DefaultMarketDataPriceStaticKey extends MarketDataPriceStaticKey {

    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return DEFAULT_MARKET_DATA_PRICE_STATIC_KEY;
    }
}
