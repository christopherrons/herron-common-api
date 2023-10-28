package com.herron.exchange.common.api.common.datastructures;

import com.herron.exchange.common.api.common.api.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TimeBoundPriorityQueue<T extends Event> extends PriorityQueue<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeBoundPriorityQueue.class);
    private final int maxTimeInMs;
    private final transient PriorityQueue<T> timeBoundQueue;

    public TimeBoundPriorityQueue(int maxTimeInMs) {
        this(maxTimeInMs, Comparator.comparing(Event::timeOfEvent));
    }

    public TimeBoundPriorityQueue(int maxTimeInMs, Comparator<T> comparator) {
        this(maxTimeInMs, new PriorityQueue<>(comparator));
    }

    public TimeBoundPriorityQueue(int maxTimeInMs, PriorityQueue<T> queue) {
        this.maxTimeInMs = maxTimeInMs;
        this.timeBoundQueue = queue;
    }

    public List<T> addItemThenPurge(T item) {
        if (item == null) {
            LOGGER.warn("Unable to add null item");
        }
        timeBoundQueue.add(item);
        return purgeItems(item);
    }

    private List<T> purgeItems(T item) {
        List<T> timeExceedingItems = new ArrayList<>();
        while (timeBoundQueue.peek() != null && isTimeExceeded(item, timeBoundQueue.peek())) {
            timeExceedingItems.add(timeBoundQueue.poll());
        }
        return timeExceedingItems;
    }

    private boolean isTimeExceeded(T currentItem, T item) {
        return currentItem.timeOfEvent().timeBetweenMs(item.timeOfEvent()) > maxTimeInMs;
    }
}
