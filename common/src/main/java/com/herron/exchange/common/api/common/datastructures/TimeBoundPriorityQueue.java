package com.herron.exchange.common.api.common.datastructures;

import com.herron.exchange.common.api.common.api.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TimeBoundPriorityQueue<T extends Message> extends PriorityQueue<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeBoundPriorityQueue.class);
    private final int timeInMs;
    private final transient PriorityQueue<T> timeBoundQueue;

    public TimeBoundPriorityQueue(int timeInMs) {
        this(timeInMs, Comparator.comparing(Message::timeStampInMs));
    }

    public TimeBoundPriorityQueue(int timeInMs, Comparator<T> comparator) {
        this(timeInMs, new PriorityQueue<>(comparator));
    }

    public TimeBoundPriorityQueue(int timeInMs, PriorityQueue<T> queue) {
        this.timeInMs = timeInMs;
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

    private boolean isTimeExceeded(final T currentItem, final T item) {
        return currentItem.timeStampInMs() - item.timeStampInMs() > timeInMs;
    }
}
