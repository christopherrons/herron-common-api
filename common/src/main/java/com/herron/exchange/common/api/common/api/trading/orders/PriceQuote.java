package com.herron.exchange.common.api.common.api.trading.orders;

import com.herron.exchange.common.api.common.api.trading.OrderbookEvent;
import com.herron.exchange.common.api.common.enums.OrderSideEnum;
import com.herron.exchange.common.api.common.messages.common.Price;

public interface PriceQuote extends OrderbookEvent {

    Price price();

    OrderSideEnum side();
}
