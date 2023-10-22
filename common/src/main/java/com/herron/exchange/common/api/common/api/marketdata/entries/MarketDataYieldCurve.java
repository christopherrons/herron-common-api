package com.herron.exchange.common.api.common.api.marketdata.entries;

import com.herron.exchange.common.api.common.api.marketdata.MarketDataEntry;
import com.herron.exchange.common.api.common.api.marketdata.statickeys.MarketDataYieldCurveStaticKey;
import com.herron.exchange.common.api.common.curves.YieldCurve;

public interface MarketDataYieldCurve extends MarketDataEntry {

    YieldCurve yieldCurve();

    @Override
    MarketDataYieldCurveStaticKey staticKey();
}
