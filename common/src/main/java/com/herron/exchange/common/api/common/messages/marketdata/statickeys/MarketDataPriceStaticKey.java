package com.herron.exchange.common.api.common.messages.marketdata.statickeys;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.StaticKey;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.MARKET_DATA_PRICE_STATIC_KEY;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMarketDataPriceStaticKey.Builder.class)
public interface MarketDataPriceStaticKey extends StaticKey {

    String instrumentId();

    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return MARKET_DATA_PRICE_STATIC_KEY;
    }
}
