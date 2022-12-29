package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.StateChangeEnum;

public interface StateChange extends Message {

    String orderbookId();
    StateChangeEnum getStateChangeEnum();
}
