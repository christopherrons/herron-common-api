package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.RequestStatus;

public interface Request extends Message {

    long requestId();

    Response createResponse(long timeStampInMs,
                            long requestId,
                            RequestStatus requestStatus,
                            String responseMessage);
}
