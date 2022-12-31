package com.herron.exchange.common.api.common.messages.herron;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.OrderbookData;
import com.herron.exchange.common.api.common.enums.MatchingAlgorithmEnum;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronOrderbookData(String orderbookId,
                                  String instrumentId,
                                  MatchingAlgorithmEnum matchingAlgorithm,
                                  String tradingCurrency,
                                  double minTradeVolume,
                                  long timeStampInMs) implements OrderbookData {

    public HerronOrderbookData(OrderbookData orderbookData) {
        this(orderbookData.orderbookId(),
                orderbookData.instrumentId(),
                orderbookData.matchingAlgorithm(),
                orderbookData.tradingCurrency(),
                orderbookData.minTradeVolume(),
                orderbookData.timeStampInMs());
    }

    @Override
    public HerronOrderbookData getCopy() {
        return new HerronOrderbookData(this);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_ORDERBOOK_DATA;
    }

}
