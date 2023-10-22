package com.herron.exchange.common.api.common.messages.marketdata.entries;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.marketdata.MarketDataEntry;
import com.herron.exchange.common.api.common.enums.PriceType;
import com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.marketdata.statickeys.MarketDataPriceStaticKey;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.MarketDataMessageTypeEnum.MARKET_DATA_PRICE;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMarketDataPrice.Builder.class)
@SuppressWarnings("immutables:from")
public interface MarketDataPrice extends MarketDataEntry {

    PriceType priceType();

    Price price();

    @Override
    MarketDataPriceStaticKey staticKey();

    @Value.Derived
    default MarketDataMessageTypeEnum messageType() {
        return MARKET_DATA_PRICE;
    }

}
