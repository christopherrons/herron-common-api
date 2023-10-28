package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.EventType;
import com.herron.exchange.common.api.common.messages.common.Timestamp;

public interface Event extends Message {
    EventType eventType();

    Timestamp timeOfEvent();
}
