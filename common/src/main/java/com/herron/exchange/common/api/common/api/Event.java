package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.EventType;

public interface Event extends Message {
    EventType eventType();

    long timeOfEventMs();

}
