package com.herron.exchange.common.api.common.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BusinessCalendarTest {

    @Test
    void test_weekend() {
        var calendar = BusinessCalendar.defaultWeekendCalendar();
        assertTrue(calendar.isHolidayOrWeekend(LocalDate.of(2023, 9, 10)));
    }

    @Test
    void test_market_open() {
        var calendar = BusinessCalendar.defaultWeekendCalendar();
        assertTrue(calendar.isMarketOpen(LocalDateTime.of(2023, 9, 18, 14, 0, 0)));
    }

    @Test
    void test_christmas() {
        var calendar = new BusinessCalendar("id", Set.of(), Set.of(), Set.of(MonthDay.of(12, 24)), new BusinessCalendar.MarketHours(9, 17), Map.of(), Map.of());
        assertTrue(calendar.isHolidayOrWeekend(LocalDate.of(2023, 12, 24)));
    }

    @Test
    void test_market_closed() {
        var calendar = new BusinessCalendar("id", Set.of(), Set.of(), Set.of(MonthDay.of(12, 24)), new BusinessCalendar.MarketHours(9, 17), Map.of(), Map.of());
        assertFalse(calendar.isMarketOpen(LocalDateTime.of(2023, 9, 18, 8, 59, 59)));
    }
}