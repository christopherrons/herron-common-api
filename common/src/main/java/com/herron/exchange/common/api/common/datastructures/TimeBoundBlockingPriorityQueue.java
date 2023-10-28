package com.herron.exchange.common.api.common.datastructures;

import com.herron.exchange.common.api.common.api.Event;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

public class TimeBoundBlockingPriorityQueue<T extends Event> extends PriorityBlockingQueue<T> {
    private final int timeStamp;
    private final transient PriorityBlockingQueue<T> timeBoundQueue;

    public TimeBoundBlockingPriorityQueue(int timeStamp) {
        this(timeStamp, Comparator.comparing(Event::timeOfEvent));
    }

    public TimeBoundBlockingPriorityQueue(int timeStamp, Comparator<T> comparator) {
        this(timeStamp, new PriorityBlockingQueue<>(100, comparator));
    }

    public TimeBoundBlockingPriorityQueue(int timeStamp, PriorityBlockingQueue<T> queue) {
        this.timeStamp = timeStamp;
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
        return currentItem.timeOfEvent().timeBetweenMs(item.timeOfEvent()) > timeStamp;
    }
}
