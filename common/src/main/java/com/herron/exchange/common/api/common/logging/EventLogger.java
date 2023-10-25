package com.herron.exchange.common.api.common.logging;

import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

public class EventLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventLogger.class);
    private static final double MILLI_TO_SEK = 1 / 1000.0;
    private static final int MESSAGE_UPDATE_INTERVAL = 1000;
    private final AtomicLong totalNrOfEvents = new AtomicLong(0);
    private final int messageUpdateInterval;
    private final String id;
    private final AtomicLong lastUpdateTimeNrOfEvents = new AtomicLong(0);
    private Instant startTime;
    private Instant lastLogUpdateTime;

    public EventLogger() {
        this("");
    }

    public EventLogger(String id) {
        this(id, MESSAGE_UPDATE_INTERVAL);
    }

    public EventLogger(String id, int messageUpdateInterval) {
        this.messageUpdateInterval = messageUpdateInterval;
        this.id = StringUtils.isEmpty(id) ? id : id + ":";
    }

    public void logEvent() {
        if (totalNrOfEvents.get() == 0) {
            startTime = Instant.now();
            lastLogUpdateTime = startTime;
        }
        try {
            long currentNrOfEvents = totalNrOfEvents.incrementAndGet();
            if (currentNrOfEvents % messageUpdateInterval == 0) {
                Instant currentTime = Instant.now();
                LOGGER.info("{} Message count: {}. Current event rate {}/s, average event rate {}/s", id, totalNrOfEvents.get(), (long) getCurrentEventsPerSecond(currentTime), (long) getAverageEventsPerSecond(currentTime));
                lastLogUpdateTime = currentTime;
                lastUpdateTimeNrOfEvents.set(currentNrOfEvents);
            }
        } catch (ArithmeticException ignore) {
        }
    }

    private double getCurrentEventsPerSecond(Instant currentTime) {
        return (totalNrOfEvents.get() - lastUpdateTimeNrOfEvents.get()) / (MILLI_TO_SEK * (currentTime.toEpochMilli() - lastLogUpdateTime.toEpochMilli()));
    }

    private double getAverageEventsPerSecond(Instant currentTime) {
        return totalNrOfEvents.get() / (MILLI_TO_SEK * (currentTime.toEpochMilli() - startTime.toEpochMilli()));
    }

    public long totalNrOfEvents() {
        return totalNrOfEvents.get();
    }
}
