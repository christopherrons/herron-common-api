package com.herron.exchange.common.api.common.model;

import com.herron.exchange.common.api.common.messages.common.DefaultBusinessCalendar;
import com.herron.exchange.common.api.common.messages.common.ImmutableDefaultBusinessCalendar;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DefaultBusinessCalendarTest {

    @Test
    void test_weekend() {
        var calendar = DefaultBusinessCalendar.defaultWeekendCalendar();
        assertTrue(calendar.isHolidayOrWeekend(LocalDate.of(2023, 9, 10)));
    }

    @Test
    void test_christmas() {
        var calendar = ImmutableDefaultBusinessCalendar.builder()
                .calendarId("id")
                .reoccurringHolidays(Set.of(MonthDay.of(12, 24)))
                .build();
        assertTrue(calendar.isHolidayOrWeekend(LocalDate.of(2023, 12, 24)));
    }
}