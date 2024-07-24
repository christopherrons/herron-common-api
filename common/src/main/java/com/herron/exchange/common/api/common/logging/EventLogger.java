package com.herron.exchange.common.api.common.logging;

import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

public class EventLogger {

    private final Logger logger;
    private static final double MILLI_TO_SEC = 1 / 1000.0;
    private static final int MESSAGE_UPDATE_INTERVAL = 100000;
    private final AtomicLong totalNrOfEvents = new AtomicLong(0);
    private final int messageUpdateInterval;
    private final String id;
    private final AtomicLong lastUpdateTimeNrOfEvents = new AtomicLong(0);
    private Instant startTime;
    private Instant lastLogUpdateTime;

    public EventLogger(String id, Logger logger) {
        this(id, logger, MESSAGE_UPDATE_INTERVAL);
    }

    public EventLogger(String id, Logger logger, int messageUpdateInterval) {
        this.messageUpdateInterval = messageUpdateInterval;
        this.logger = logger;
        this.id = StringUtils.isEmpty(id) ? id : id + ":";
    }

    public synchronized void logEvent() {
        if (totalNrOfEvents.get() == 0) {
            startTime = Instant.now();
            lastLogUpdateTime = startTime;
        }

        long currentNrOfEvents = totalNrOfEvents.incrementAndGet();
        if (currentNrOfEvents % messageUpdateInterval == 0) {
            Instant currentTime = Instant.now();
            double currentEventRate = getCurrentEventsPerSecond(currentTime);
            double averageEventRate = getAverageEventsPerSecond(currentTime);

            logger.info(String.format("%s Message count: %d. Current event rate: %.2f/s, average event rate: %.2f/s",
                    id, currentNrOfEvents, currentEventRate, averageEventRate));

            lastLogUpdateTime = currentTime;
            lastUpdateTimeNrOfEvents.set(currentNrOfEvents);
        }
    }

    private double getCurrentEventsPerSecond(Instant currentTime) {
        long elapsedMillis = currentTime.toEpochMilli() - lastLogUpdateTime.toEpochMilli();
        long eventCountDelta = totalNrOfEvents.get() - lastUpdateTimeNrOfEvents.get();
        return elapsedMillis > 0 ? eventCountDelta / (elapsedMillis * MILLI_TO_SEC) : 0.0;
    }

    private double getAverageEventsPerSecond(Instant currentTime) {
        long elapsedMillis = currentTime.toEpochMilli() - startTime.toEpochMilli();
        return elapsedMillis > 0 ? totalNrOfEvents.get() / (elapsedMillis * MILLI_TO_SEC) : 0.0;
    }

    public long getTotalNrOfEvents() {
        return totalNrOfEvents.get();
    }
}
