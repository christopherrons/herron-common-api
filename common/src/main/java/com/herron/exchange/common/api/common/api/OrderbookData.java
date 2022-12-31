package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.MatchingAlgorithmEnum;

public interface OrderbookData extends Message {

    String orderbookId();

    String instrumentId();

    MatchingAlgorithmEnum matchingAlgorithm();

    String tradingCurrency();

    double minTradeVolume();

}
