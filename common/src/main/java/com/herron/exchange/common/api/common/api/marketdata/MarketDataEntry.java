package com.herron.exchange.common.api.common.api.marketdata;

import com.herron.exchange.common.api.common.api.Message;

public interface MarketDataEntry extends Message {

    StaticKey staticKey();

    TimeComponentKey timeComponentKey();
}
