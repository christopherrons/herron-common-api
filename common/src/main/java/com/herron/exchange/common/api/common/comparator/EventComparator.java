package com.herron.exchange.common.api.common.comparator;

import com.herron.exchange.common.api.common.api.Event;

import java.util.Comparator;

public class EventComparator<T extends Event> implements Comparator<T> {
    @Override
    public int compare(T event, T otherEvent) {
        return event.timeOfEvent().compareTo(otherEvent.timeOfEvent());

    }
}
