package com.herron.exchange.common.api.common.api.broadcasts.requests;

import com.herron.exchange.common.api.common.api.trading.statechange.StateChange;

public interface StateChangeRequest extends Request {

    StateChange stateChange();
}
