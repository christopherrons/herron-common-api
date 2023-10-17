package com.herron.exchange.common.api.common.messages.marketdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.statickeys.MarketDataPriceStaticKey;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultMarketDataPriceStaticKey.Builder.class)
public interface DefaultMarketDataPriceStaticKey extends MarketDataPriceStaticKey {

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.DEFAULT_MARKET_DATA_PRICE_STATIC_KEY;
    }
}
