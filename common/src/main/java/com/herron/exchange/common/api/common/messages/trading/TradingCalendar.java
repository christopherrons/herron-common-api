package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum;
import jakarta.annotation.Nullable;
import org.immutables.value.Value;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;
import java.util.Map;

import static com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum.TRADING_CALENDAR;

@Value.Immutable
@JsonDeserialize(builder = ImmutableTradingCalendar.Builder.class)
public interface TradingCalendar extends Message {
    String calendarId();

    @Nullable
    TradingCalendar.TradingHours preTradingHours();

    @Nullable
    TradingCalendar.TradingHours openAuctionTradingHours();

    TradingCalendar.TradingHours continuousTradingHours();

    @Nullable
    TradingCalendar.TradingHours closeAuctionTradingHours();

    @Nullable
    TradingCalendar.TradingHours postTradingHours();

    @Nullable
    TradingCalendar.TradingHours closedTradingHours();

    @Value.Default
    default Map<LocalDate, TradingCalendar.TradingHours> holidayMarketHours() {
        return Map.of();
    }

    @Value.Default
    default Map<MonthDay, TradingCalendar.TradingHours> reoccurringHolidayMarketHours() {
        return Map.of();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    record TradingHours(LocalTime start, LocalTime end) {
        public TradingHours(int startHour, int endHour) {
            this(LocalTime.of(startHour, 0, 0), LocalTime.of(endHour, 0, 0));
        }

        boolean isOpen(LocalTime time) {
            return !time.isBefore(start) && !time.isAfter(end);
        }
    }

    @Value.Derived
    default TradingMessageTypeEnum messageType() {
        return TRADING_CALENDAR;
    }


    static TradingCalendar twentyFourSevenTradingCalendar() {
        return ImmutableTradingCalendar.builder()
                .calendarId("24/7 trading calendar")
                .continuousTradingHours(new TradingHours(LocalTime.MIDNIGHT, LocalTime.MAX))
                .build();
    }

    static TradingCalendar nineToFiveTradingCalendar() {
        return ImmutableTradingCalendar.builder()
                .calendarId("9 to 17 trading calendar")
                .continuousTradingHours(new TradingHours(LocalTime.of(9, 0), LocalTime.of(17, 0)))
                .build();
    }
}
