package com.herron.exchange.common.api.common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

public class EventLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventLogger.class);

    private static double MILLI_TO_SEK = 1.0 / 1000;
    private static final int MESSAGE_UPDATE_INTERVAL = 1000;
    private final AtomicLong totalNrOfEvents = new AtomicLong();
    private final Instant startTime = Instant.now();
    private final String eventLoggDescription;
    private Instant lastLogUpdateTime = Instant.now();
    private AtomicLong lastUpdateTimeNrOfEvents = new AtomicLong();

    public EventLogger() {
        this("");
    }

    public EventLogger(String eventLoggDescription) {
        this.eventLoggDescription = eventLoggDescription;
    }

    public void logEvent() {
        try {
            if (totalNrOfEvents.incrementAndGet() % MESSAGE_UPDATE_INTERVAL == 0) {
                Instant currentTime = Instant.now();
                LOGGER.info("{}: Messages received: {}. Current event rate {}/s, average event rate {}/s",
                        eventLoggDescription, totalNrOfEvents.get(), getCurrentEventsPerSecond(currentTime), getAverageEventsPerSecond(currentTime));
                lastLogUpdateTime = currentTime;
                lastUpdateTimeNrOfEvents = new AtomicLong(totalNrOfEvents.get());
            }
        } catch (ArithmeticException ignore) {
        }
    }

    private long getCurrentEventsPerSecond(Instant currentTime) {
        return (long) MILLI_TO_SEK * (totalNrOfEvents.get() - lastUpdateTimeNrOfEvents.get()) / (currentTime.toEpochMilli() - lastLogUpdateTime.toEpochMilli());
    }

    private long getAverageEventsPerSecond(Instant currentTime) {
        return (long) MILLI_TO_SEK * totalNrOfEvents.get() / (currentTime.toEpochMilli() - lastLogUpdateTime.toEpochMilli());
    }
}
