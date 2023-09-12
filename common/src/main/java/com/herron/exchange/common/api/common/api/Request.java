package com.herron.exchange.common.api.common.api;

public interface Request extends Event {

    long requestId();

    Response createOkResponse(long timeStampInMs,
                              long requestId,
                              String responseMessage);

    Response createErrorResponse(long timeStampInMs,
                                 long requestId,
                                 String errorMessage);
}
