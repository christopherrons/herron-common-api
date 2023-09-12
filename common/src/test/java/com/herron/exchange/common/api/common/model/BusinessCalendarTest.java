package com.herron.exchange.common.api.common.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BusinessCalendarTest {

    @Test
    void test_weekend() {
        var calendar = BusinessCalendar.defaultWeekendCalendar();
        assertTrue(calendar.isHolidayOrWeekend(LocalDate.of(2023, 9, 10)));
    }

    @Test
    void test_christmas() {
        var calendar = new BusinessCalendar("id", Set.of(), Set.of(), Set.of(MonthDay.of(12, 24)));
        assertTrue(calendar.isHolidayOrWeekend(LocalDate.of(2023, 12, 24)));
    }
}