package com.herron.exchange.common.api.common.api.marketdata;

import com.herron.exchange.common.api.common.api.marketdata.statickeys.MarketDataPriceStaticKey;
import com.herron.exchange.common.api.common.enums.PriceType;
import com.herron.exchange.common.api.common.messages.common.Price;

public interface MarketDataPrice extends MarketDataEntry {

    PriceType priceType();

    Price price();

    @Override
    MarketDataPriceStaticKey staticKey();
}
