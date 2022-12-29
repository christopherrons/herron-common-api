package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

public interface Message {

    MessageTypesEnum messageType();
    long timeStampInMs();

}
