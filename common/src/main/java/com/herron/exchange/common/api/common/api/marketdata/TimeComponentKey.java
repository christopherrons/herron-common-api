package com.herron.exchange.common.api.common.api.marketdata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface TimeComponentKey {

    LocalDateTime timeOfEvent();

    default LocalDate date() {
        return timeOfEvent().toLocalDate();
    }

    default LocalTime time() {
        return timeOfEvent().toLocalTime();
    }
}
