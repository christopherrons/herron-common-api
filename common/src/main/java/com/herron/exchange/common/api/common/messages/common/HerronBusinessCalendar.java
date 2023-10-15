package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.exchange.BusinessCalendar;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

import java.util.Set;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

@Value.Immutable
@JsonDeserialize(builder = ImmutableHerronBusinessCalendar.Builder.class)
public interface HerronBusinessCalendar extends BusinessCalendar {

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_BUSINESS_CALENDAR;
    }

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
