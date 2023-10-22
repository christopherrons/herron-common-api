package com.herron.exchange.common.api.common.api.marketdata;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.MarketDataRequestTimeFilter;

public interface MarketDataRequest extends Message {

    MarketDataRequestTimeFilter timeFilter();

    StaticKey staticKey();

    TimeComponentKey timeComponentKey();
}
