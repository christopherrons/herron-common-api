package com.herron.exchange.common.api.common.api.referencedata.orderbook;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.api.referencedata.exchange.TradingCalendar;
import com.herron.exchange.common.api.common.api.referencedata.instruments.Instrument;
import com.herron.exchange.common.api.common.enums.AuctionAlgorithmEnum;
import com.herron.exchange.common.api.common.enums.MatchingAlgorithmEnum;
import com.herron.exchange.common.api.common.messages.refdata.HerronOrderbookData;
import org.immutables.value.Value;

import java.time.LocalDate;

@JsonSubTypes({
        @JsonSubTypes.Type(value = HerronOrderbookData.class, name = "HEOB"),
})
public interface OrderbookData extends Message {

    TradingCalendar tradingCalendar();

    @Value.Default
    default double tickValue() {
        return 1;
    }

    @Value.Default
    default double tickSize() {
        return 1;
    }

    String orderbookId();

    @Value.Default
    default String orderbookName() {
        return orderbookId();
    }

    Instrument instrument();

    @Value.Default
    default LocalDate firstTradingDate() {
        return instrument().firstTradingDate();
    }

    @Value.Default
    default LocalDate lastTradingDate() {
        return instrument().lastTradingDate();
    }

    MatchingAlgorithmEnum matchingAlgorithm();

    @Value.Default
    default String tradingCurrency() {
        return instrument().currency();
    }

    @Value.Default
    default double minTradeVolume() {
        return 1;
    }

    AuctionAlgorithmEnum auctionAlgorithm();

}
