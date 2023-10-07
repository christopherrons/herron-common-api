package com.herron.exchange.common.api.common.api;

import org.immutables.value.Value;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Set;

public interface BusinessCalendar {
    String calendarId();

    @Value.Default
    default Set<DayOfWeek> weekends() {
        return Set.of();
    }

    @Value.Default
    default Set<LocalDate> holidays() {
        return Set.of();
    }

    @Value.Default
    default Set<MonthDay> reoccurringHolidays() {
        return Set.of();
    }

    default LocalDate getFirstDateBeforeHoliday(LocalDate date) {
        LocalDate preHolidayDate = date;
        while (isHolidayOrWeekend(date)) {
            preHolidayDate = preHolidayDate.minusDays(1);
        }
        return preHolidayDate;
    }

    default LocalDate getFirstDateAfterHoliday(LocalDate date) {
        LocalDate preHolidayDate = date;
        while (isHolidayOrWeekend(date)) {
            preHolidayDate = preHolidayDate.plusDays(1);
        }
        return preHolidayDate;
    }


    default boolean isHolidayOrWeekend(LocalDate date) {
        return holidays().contains(date) ||
                reoccurringHolidays().contains(MonthDay.from(date)) ||
                weekends().contains(date.getDayOfWeek());
    }
}
