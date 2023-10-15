package com.herron.exchange.common.api.common.api.referencedata.exchange;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.messages.trading.HerronTradingCalendar;
import jakarta.annotation.Nullable;
import org.immutables.value.Value;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;
import java.util.Map;

@JsonSubTypes({
        @JsonSubTypes.Type(value = HerronTradingCalendar.class, name = "HETC"),
})
public interface TradingCalendar extends Message {
    String calendarId();

    @Nullable
    TradingHours preTradingHours();

    @Nullable
    TradingHours openAuctionTradingHours();

    TradingHours continuousTradingHours();

    @Nullable
    TradingHours closeAuctionTradingHours();

    @Value.Default
    default Map<LocalDate, TradingHours> holidayMarketHours() {
        return Map.of();
    }

    @Value.Default
    default Map<MonthDay, TradingHours> reoccurringHolidayMarketHours() {
        return Map.of();
    }

    record TradingHours(LocalTime start, LocalTime end) {
        public TradingHours(int startHour, int endHour) {
            this(LocalTime.of(startHour, 0, 0), LocalTime.of(endHour, 0, 0));
        }

        boolean isOpen(LocalTime time) {
            return !time.isBefore(start) && !time.isAfter(end);
        }
    }
}
