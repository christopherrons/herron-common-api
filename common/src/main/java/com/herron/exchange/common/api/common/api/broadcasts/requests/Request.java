package com.herron.exchange.common.api.common.api.broadcasts.requests;

import com.herron.exchange.common.api.common.api.broadcasts.response.Response;
import com.herron.exchange.common.api.common.api.Event;

public interface Request extends Event {

    long requestId();

    Response createOkResponse(long timeStampInMs,
                              long requestId,
                              String responseMessage);

    Response createErrorResponse(long timeStampInMs,
                                 long requestId,
                                 String errorMessage);
}
