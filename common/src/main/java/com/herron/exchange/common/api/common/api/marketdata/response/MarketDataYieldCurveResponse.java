package com.herron.exchange.common.api.common.api.marketdata.response;

import com.herron.exchange.common.api.common.api.marketdata.MarketDataResponse;
import com.herron.exchange.common.api.common.api.marketdata.entries.MarketDataYieldCurve;

public interface MarketDataYieldCurveResponse extends MarketDataResponse {

    MarketDataYieldCurve yieldCurveEntry();
}
