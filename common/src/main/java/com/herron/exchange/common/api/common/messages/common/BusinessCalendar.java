package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.messagetypes.ReferenceDataMessageTypeEnum;
import org.immutables.value.Value;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Set;

import static com.herron.exchange.common.api.common.enums.messagetypes.ReferenceDataMessageTypeEnum.BUSINESS_CALENDAR;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

@Value.Immutable
@JsonDeserialize(builder = ImmutableBusinessCalendar.Builder.class)
public interface BusinessCalendar extends Message {

    static BusinessCalendar noHolidayCalendar() {
        return ImmutableBusinessCalendar.builder()
                .calendarId("noHolidayCalendar")
                .holidays(Set.of())
                .weekends(Set.of())
                .reoccurringHolidays(Set.of())
                .build();
    }

    static BusinessCalendar defaultWeekendCalendar() {
        return ImmutableBusinessCalendar.builder()
                .calendarId("weekendCalendar")
                .holidays(Set.of())
                .weekends(Set.of(SATURDAY, SUNDAY))
                .reoccurringHolidays(Set.of())
                .build();
    }

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

    @Value.Derived
    default ReferenceDataMessageTypeEnum messageType() {
        return BUSINESS_CALENDAR;
    }
}
