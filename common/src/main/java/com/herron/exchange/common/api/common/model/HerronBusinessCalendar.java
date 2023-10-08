package com.herron.exchange.common.api.common.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.exchange.BusinessCalendar;
import org.immutables.value.Value;

import java.util.Set;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

@Value.Immutable
@JsonDeserialize(builder = ImmutableHerronBusinessCalendar.Builder.class)
public interface HerronBusinessCalendar extends BusinessCalendar {

    static BusinessCalendar noHolidayCalendar() {
        return ImmutableHerronBusinessCalendar.builder()
                .calendarId("noHolidayCalendar")
                .holidays(Set.of())
                .weekends(Set.of())
                .reoccurringHolidays(Set.of())
                .build();
    }

    static BusinessCalendar defaultWeekendCalendar() {
        return ImmutableHerronBusinessCalendar.builder()
                .calendarId("weekendCalendar")
                .holidays(Set.of())
                .weekends(Set.of(SATURDAY, SUNDAY))
                .reoccurringHolidays(Set.of())
                .build();
    }
}
