package com.herron.exchange.common.api.common.datastructures;

import com.herron.exchange.common.api.common.api.Event;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

public class TimeBoundBlockingPriorityQueue<T extends Event> extends PriorityBlockingQueue<T> {
    private final int timeInMs;
    private final transient PriorityBlockingQueue<T> timeBoundQueue;

    public TimeBoundBlockingPriorityQueue(int timeInMs) {
        this(timeInMs, Comparator.comparing(Event::timeStampInMs));
    }

    public TimeBoundBlockingPriorityQueue(int timeInMs, Comparator<T> comparator) {
        this(timeInMs, new PriorityBlockingQueue<>(100, comparator));
    }

    public TimeBoundBlockingPriorityQueue(int timeInMs, PriorityBlockingQueue<T> queue) {
        this.timeInMs = timeInMs;
        this.timeBoundQueue = queue;
    }

    public List<T> addItemThenPurge(T item) {
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
        return currentItem.timeStampInMs() - item.timeStampInMs() > timeInMs;
    }
}
