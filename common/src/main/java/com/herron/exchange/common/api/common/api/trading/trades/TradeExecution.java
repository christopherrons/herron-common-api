package com.herron.exchange.common.api.common.api.trading.trades;

import com.herron.exchange.common.api.common.api.trading.OrderbookEvent;

import java.util.List;

public interface TradeExecution extends OrderbookEvent {

    List<OrderbookEvent> messages();

}
