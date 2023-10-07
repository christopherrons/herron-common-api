package com.herron.exchange.common.api.common.api;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;
import java.util.Map;

public interface TradingCalendar {
    String calendarId();

    MarketHours preTradingHours();

    MarketHours openAuctionTradingHours();

    MarketHours continuousTradingHours();

    MarketHours closeAuctionTradingHours();

    Map<LocalDate, MarketHours> holidayMarketHours();

    Map<MonthDay, MarketHours> reoccurringHolidayMarketHours();

    record MarketHours(LocalTime start, LocalTime end) {
        public MarketHours(int startHour, int endHour) {
            this(LocalTime.of(startHour, 0, 0), LocalTime.of(endHour, 0, 0));
        }

        boolean isOpen(LocalTime time) {
            return !time.isBefore(start) && !time.isAfter(end);
        }
    }
}
