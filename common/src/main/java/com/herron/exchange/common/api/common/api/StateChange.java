package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.StateChangeTypeEnum;

public interface StateChange extends Message {

    String orderbookId();

    StateChangeTypeEnum stateChangeType();
}
