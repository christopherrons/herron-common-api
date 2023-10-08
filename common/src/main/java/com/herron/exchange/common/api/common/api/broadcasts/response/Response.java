package com.herron.exchange.common.api.common.api.broadcasts.response;

import com.herron.exchange.common.api.common.api.Event;
import com.herron.exchange.common.api.common.enums.RequestStatus;

public interface Response extends Event {

    long requestId();

    RequestStatus requestStatus();

    String responseMessage();
}
