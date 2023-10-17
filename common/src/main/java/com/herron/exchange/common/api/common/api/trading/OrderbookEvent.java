package com.herron.exchange.common.api.common.api.trading;

import com.herron.exchange.common.api.common.api.Event;

public interface OrderbookEvent extends Event {
    String orderbookId();
}
