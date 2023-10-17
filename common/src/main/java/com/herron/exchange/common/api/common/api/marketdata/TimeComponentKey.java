package com.herron.exchange.common.api.common.api.marketdata;

import com.herron.exchange.common.api.common.api.Message;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface TimeComponentKey extends Message {

    LocalDateTime timeOfEvent();

    default LocalDate date() {
        return timeOfEvent().toLocalDate();
    }

    default LocalTime time() {
        return timeOfEvent().toLocalTime();
    }
}
