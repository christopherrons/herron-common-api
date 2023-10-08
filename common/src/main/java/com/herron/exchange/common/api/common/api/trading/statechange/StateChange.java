package com.herron.exchange.common.api.common.api.trading.statechange;

import com.herron.exchange.common.api.common.api.Event;
import com.herron.exchange.common.api.common.enums.StateChangeTypeEnum;

public interface StateChange extends Event {

    String orderbookId();

    StateChangeTypeEnum stateChangeType();
}
