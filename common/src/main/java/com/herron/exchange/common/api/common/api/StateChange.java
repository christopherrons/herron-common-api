package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.StateChangeTypeEnum;

public interface StateChange extends Event {

    String orderbookId();

    StateChangeTypeEnum stateChangeType();
}
