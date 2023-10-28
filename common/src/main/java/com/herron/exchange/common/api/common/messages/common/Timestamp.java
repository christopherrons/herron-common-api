package com.herron.exchange.common.api.common.messages.common;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;


public record Timestamp(long timeStampMs, ZoneId zoneId) implements Comparable<Timestamp> {
    private static final double SECOND_TO_MILLI = 1000;
    private static final double NANO_TO_MILLI = 1000_000;

    public static Timestamp now(ZoneId zoneId) {
        return from(Instant.now(), zoneId);
    }

    public static Timestamp now() {
        return from(Instant.now(), ZoneId.of("UTC"));
    }

    public static Timestamp from(long milliSeconds) {
        return from(Instant.ofEpochMilli(milliSeconds), ZoneId.of("UTC"));
    }

    public static Timestamp from(Instant instant, ZoneId zoneId) {
        var epochMilli = (instant.getEpochSecond() * SECOND_TO_MILLI) + (instant.getNano() / NANO_TO_MILLI);
        return new Timestamp((long) epochMilli, zoneId);
    }

    public static Timestamp from(Instant instant) {
        return from(instant, ZoneId.of("UTC"));
    }

    public static Timestamp from(LocalDateTime localDateTime, ZoneId zoneId) {
        Instant instant = localDateTime.atZone(zoneId).toInstant();
        return from(instant, zoneId);
    }

    public static Timestamp from(LocalDateTime localDateTime) {
        return from(localDateTime, ZoneId.of("UTC"));
    }

    public static Timestamp from(LocalDate localDate, ZoneId zoneId) {
        Instant instant = localDate.atStartOfDay(zoneId).toInstant();
        return from(instant, zoneId);
    }

    public static Timestamp from(LocalDate localDate) {
        return from(localDate, ZoneId.of("UTC"));
    }

    public boolean isBeforeOrAt(Timestamp other) {
        return this.compareTo(other) <= 0;
    }

    public boolean isAfterOrAt(Timestamp other) {
        return this.compareTo(other) >= 0;
    }

    public boolean isBefore(Timestamp other) {
        return this.compareTo(other) < 0;
    }

    public boolean isAfter(Timestamp other) {
        return this.compareTo(other) > 0;
    }

    public long timeBetweenMs(Timestamp other) {
        return this.timeStampMs - other.timeStampMs;
    }

    @Override
    public int compareTo(Timestamp other) {
        return Long.compare(this.timeStampMs, other.timeStampMs);
    }
}
