package com.herron.exchange.common.api.common.messages.marketdata.entries;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.entries.MarketDataPrice;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.DEFAULT_MARKET_DATA_PRICE;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultMarketDataPrice.Builder.class)
@SuppressWarnings("immutables:from")
public interface DefaultMarketDataPrice extends MarketDataPrice {

    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return DEFAULT_MARKET_DATA_PRICE;
    }

}
