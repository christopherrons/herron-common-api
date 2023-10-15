package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.referencedata.exchange.TradingCalendar;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

import java.time.LocalTime;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultTradingCalendar.Builder.class)
public interface DefaultTradingCalendar extends TradingCalendar {

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.DEFAULT_TRADING_CALENDAR;
    }


    static TradingCalendar twentyFourSevenTradingCalendar() {
        return ImmutableDefaultTradingCalendar.builder()
                .calendarId("24/7 trading calendar")
                .continuousTradingHours(new TradingHours(LocalTime.MIDNIGHT, LocalTime.MAX))
                .build();
    }

    static TradingCalendar nineToFiveTradingCalendar() {
        return ImmutableDefaultTradingCalendar.builder()
                .calendarId("9 to 17 trading calendar")
                .continuousTradingHours(new TradingHours(LocalTime.of(9, 0), LocalTime.of(17, 0)))
                .build();
    }
}
