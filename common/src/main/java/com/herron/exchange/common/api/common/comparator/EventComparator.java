package com.herron.exchange.common.api.common.comparator;

import com.herron.exchange.common.api.common.api.Event;

import java.util.Comparator;

public class EventComparator<T extends Event> implements Comparator<T> {
    @Override
    public int compare(T event, T otherEvent) {
        if (event.timeOfEventMs() < otherEvent.timeOfEventMs()) {
            return -1;
        }
        if (event.timeOfEventMs() > otherEvent.timeOfEventMs()) {
            return 1;
        }

        return 0;
    }
}
