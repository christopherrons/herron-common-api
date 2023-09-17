package com.herron.exchange.common.api.common.model;

import java.time.*;
import java.util.Map;
import java.util.Set;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public record BusinessCalendar(String calendarId,
                               Set<DayOfWeek> weekends,
                               Set<LocalDate> holidays,
                               Set<MonthDay> reoccurringHolidays,
                               MarketHours defaultMarketHours,
                               Map<LocalDate, MarketHours> holidayMarketHours,
                               Map<MonthDay, MarketHours> reoccurringHolidayMarketHours) {

    public static BusinessCalendar noHolidayCalendar() {
        return new BusinessCalendar("noHolidayCalendar",
                Set.of(),
                Set.of(),
                Set.of(),
                new MarketHours(LocalTime.of(9, 0, 0), LocalTime.of(17, 0, 0)),
                Map.of(),
                Map.of()
        );
    }

    public static BusinessCalendar defaultWeekendCalendar() {
        return new BusinessCalendar("defaultWeekendCalendar",
                Set.of(SATURDAY, SUNDAY),
                Set.of(),
                Set.of(),
                new MarketHours(LocalTime.of(9, 0, 0), LocalTime.of(17, 0, 0)),
                Map.of(),
                Map.of()
        );
    }

    public LocalDate getFirstDateBeforeHoliday(LocalDate date) {
        LocalDate preHolidayDate = date;
        while (isHolidayOrWeekend(date)) {
            preHolidayDate = preHolidayDate.minusDays(1);
        }
        return preHolidayDate;
    }

    public LocalDate getFirstDateAfterHoliday(LocalDate date) {
        LocalDate preHolidayDate = date;
        while (isHolidayOrWeekend(date)) {
            preHolidayDate = preHolidayDate.plusDays(1);
        }
        return preHolidayDate;
    }

    public boolean isMarketOpen(LocalDateTime localDateTime) {
        var date = localDateTime.toLocalDate();
        var monthDay = MonthDay.from(date);
        var time = localDateTime.toLocalTime();
        if (isHolidayOrWeekend(date)) {
            return false;
        }
        return (holidayMarketHours.containsKey(date) && holidayMarketHours.get(date).isOpen(time)) ||
                (reoccurringHolidayMarketHours.containsKey(monthDay) && reoccurringHolidayMarketHours.get(monthDay).isOpen(time)) ||
                defaultMarketHours.isOpen(localDateTime.toLocalTime());
    }

    public boolean isHolidayOrWeekend(LocalDate date) {
        return holidays.contains(date) ||
                reoccurringHolidays.contains(MonthDay.from(date)) ||
                weekends.contains(date.getDayOfWeek());
    }

    public record MarketHours(LocalTime start, LocalTime end) {
        public MarketHours(int startHour, int endHour) {
            this(LocalTime.of(startHour, 0, 0), LocalTime.of(endHour, 0, 0));
        }

        boolean isOpen(LocalTime time) {
            return !time.isBefore(start) && !time.isAfter(end);
        }
    }
}
