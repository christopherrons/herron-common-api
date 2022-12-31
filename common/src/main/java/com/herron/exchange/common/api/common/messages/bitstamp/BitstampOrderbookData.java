package com.herron.exchange.common.api.common.messages.bitstamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.api.OrderbookData;
import com.herron.exchange.common.api.common.enums.MatchingAlgorithmEnum;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BitstampOrderbookData(@JsonProperty("orderbookId") String orderbookId,
                                    @JsonProperty("instrumentId") String instrumentId,
                                    @JsonProperty("matchingAlgorithm") String matchingAlgorithmString,
                                    @JsonProperty("tradingCurrency") String tradingCurrency,
                                    @JsonProperty("minTradeVolume") double minTradeVolume,
                                    @JsonProperty("timeStampInMs") long timeStampInMs) implements OrderbookData {

    public BitstampOrderbookData(BitstampOrderbookData orderbookData) {
        this(orderbookData.orderbookId(),
                orderbookData.instrumentId(),
                orderbookData.matchingAlgorithmString(),
                orderbookData.tradingCurrency(),
                orderbookData.minTradeVolume(),
                orderbookData.timeStampInMs());
    }

    @Override
    public Message getCopy() {
        return new BitstampOrderbookData(this);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.BITSTAMP_ORDERBOOK_DATA;
    }

    @Override
    public MatchingAlgorithmEnum matchingAlgorithm() {
        return MatchingAlgorithmEnum.fromValue(matchingAlgorithmString);
    }
}
