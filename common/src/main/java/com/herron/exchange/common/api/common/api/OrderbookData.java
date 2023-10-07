package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.AuctionAlgorithmEnum;
import com.herron.exchange.common.api.common.enums.MatchingAlgorithmEnum;
import org.immutables.value.Value;

public interface OrderbookData extends Message {

    TradingCalendar tradingCalendar();

    double tickValue();

    double tickSize();

    String orderbookId();

    String orderbookName();

    String instrumentId();

    MatchingAlgorithmEnum matchingAlgorithm();

    String tradingCurrency();

    @Value.Default
    default double minTradeVolume() {
        return 1;
    }

    AuctionAlgorithmEnum auctionAlgorithm();

}
