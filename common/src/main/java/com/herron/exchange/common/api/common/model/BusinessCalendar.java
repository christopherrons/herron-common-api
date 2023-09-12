package com.herron.exchange.common.api.common.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Set;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public record BusinessCalendar(String calendarId, Set<DayOfWeek> weekends, Set<LocalDate> holidays, Set<MonthDay> reoccurringHolidays) {

    public static BusinessCalendar noHolidayCalendar() {
        return new BusinessCalendar("noHolidayCalendar", Set.of(), Set.of(), Set.of());
    }

    public static BusinessCalendar defaultWeekendCalendar() {
        return new BusinessCalendar("defaultWeekendCalendar", Set.of(SATURDAY, SUNDAY), Set.of(), Set.of());
    }

    public LocalDate getDateBeforeHoliday(LocalDate date) {
        LocalDate preHolidayDate = date;
        while (isHolidayOrWeekend(date)) {
            preHolidayDate = preHolidayDate.minusDays(1);
        }
        return preHolidayDate;
    }

    public LocalDate getDateAfterHoliday(LocalDate date) {
        LocalDate preHolidayDate = date;
        while (isHolidayOrWeekend(date)) {
            preHolidayDate = preHolidayDate.plusDays(1);
        }
        return preHolidayDate;
    }

    public boolean isHolidayOrWeekend(LocalDate date) {
        return holidays.contains(date) ||
                reoccurringHolidays.contains(MonthDay.from(date)) ||
                weekends.contains(date.getDayOfWeek());
    }
}
