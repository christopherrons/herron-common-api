package com.herron.exchange.common.api.common.datastructures;

import com.herron.exchange.common.api.common.api.Message;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TimeBoundPriorityQueue<T extends Message> extends PriorityQueue<T> {
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

    public List<T> addItemThenPurge(final T item) {
        timeBoundQueue.add(item);
        return purgeItems(item);
    }

    private List<T> purgeItems(final T item) {
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
