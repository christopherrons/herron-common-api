package com.herron.exchange.common.api.common.messages.marketdata;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.MarketDataPrice;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.MessageTypesEnum.DEFAULT_MARKET_DATA_PRICE;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultMarketDataPrice.Builder.class)
public interface DefaultMarketDataPrice extends MarketDataPrice {

    @Value.Derived
    default MessageTypesEnum messageType() {
        return DEFAULT_MARKET_DATA_PRICE;
    }

}
