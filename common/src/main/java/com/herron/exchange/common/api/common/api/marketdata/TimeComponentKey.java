package com.herron.exchange.common.api.common.api.marketdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.messages.common.Timestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface TimeComponentKey extends Message {

    Timestamp timeOfEvent();

    @JsonIgnore
    default LocalDateTime dateTime() {
        return timeOfEvent().toLocalDateTime();
    }

    @JsonIgnore
    default LocalDate date() {
        return dateTime().toLocalDate();
    }

    @JsonIgnore
    default LocalTime time() {
        return dateTime().toLocalTime();
    }
}
