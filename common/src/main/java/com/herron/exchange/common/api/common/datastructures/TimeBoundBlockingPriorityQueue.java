package com.herron.exchange.common.api.common.datastructures;

import com.herron.exchange.common.api.common.api.Message;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

public class TimeBoundBlockingPriorityQueue<T extends Message> extends PriorityBlockingQueue<T> {
    private final int timeInMs;
    private final transient PriorityBlockingQueue<T> timeBoundQueue;

    public TimeBoundBlockingPriorityQueue(int timeInMs) {
        this(timeInMs, Comparator.comparing(Message::timeStampInMs));
    }

    public TimeBoundBlockingPriorityQueue(int timeInMs, Comparator<T> comparator) {
        this(timeInMs, new PriorityBlockingQueue<>(100, comparator));
    }

    public TimeBoundBlockingPriorityQueue(int timeInMs, PriorityBlockingQueue<T> queue) {
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
