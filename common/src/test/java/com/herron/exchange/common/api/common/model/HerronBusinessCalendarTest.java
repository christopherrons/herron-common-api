package com.herron.exchange.common.api.common.model;

import com.herron.exchange.common.api.common.messages.common.HerronBusinessCalendar;
import com.herron.exchange.common.api.common.messages.common.ImmutableHerronBusinessCalendar;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HerronBusinessCalendarTest {

    @Test
    void test_weekend() {
        var calendar = HerronBusinessCalendar.defaultWeekendCalendar();
        assertTrue(calendar.isHolidayOrWeekend(LocalDate.of(2023, 9, 10)));
    }

    @Test
    void test_christmas() {
        var calendar = ImmutableHerronBusinessCalendar.builder()
                .calendarId("id")
                .reoccurringHolidays(Set.of(MonthDay.of(12, 24)))
                .build();
        assertTrue(calendar.isHolidayOrWeekend(LocalDate.of(2023, 12, 24)));
    }
}