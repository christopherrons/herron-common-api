package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.RequestStatus;

public interface Response extends Message {

    long requestId();

    RequestStatus requestStatus();

    String responseMessage();
}
