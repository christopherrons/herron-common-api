package com.herron.exchange.common.api.common.api.marketdata;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.Status;
import org.immutables.value.Value;

public interface MarketDataResponse extends Message {
    Status status();

    @Value.Default
    default String error() {
        return "";
    }
}
