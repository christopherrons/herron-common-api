package com.herron.exchange.common.api.common.api.marketdata.requests;

import com.herron.exchange.common.api.common.api.marketdata.MarketDataRequest;
import com.herron.exchange.common.api.common.api.marketdata.statickeys.MarketDataYieldCurveStaticKey;

public interface MarketDataYieldCurveRequest extends MarketDataRequest {

    String curveId();

    @Override
    MarketDataYieldCurveStaticKey staticKey();
}
