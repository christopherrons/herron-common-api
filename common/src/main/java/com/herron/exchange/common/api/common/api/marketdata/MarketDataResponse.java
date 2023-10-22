package com.herron.exchange.common.api.common.api.marketdata;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.RequestStatus;

public interface MarketDataResponse extends Message {
    RequestStatus requestStatus();
}
