package com.herron.exchange.common.api.common.api.trading.statechange;

import com.herron.exchange.common.api.common.api.trading.OrderbookEvent;
import com.herron.exchange.common.api.common.enums.StateChangeTypeEnum;

public interface StateChange extends OrderbookEvent {

    StateChangeTypeEnum stateChangeType();
}
