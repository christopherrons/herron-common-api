package com.herron.exchange.common.api.common.messages.herron;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.api.OrderbookData;
import com.herron.exchange.common.api.common.enums.MatchingAlgorithmEnum;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronOrderbookData(@JsonProperty("orderbookId") String orderbookId,
                                  @JsonProperty("instrumentId") String instrumentId,
                                  @JsonProperty("matchingAlgorithm") MatchingAlgorithmEnum matchingAlgorithm,
                                  @JsonProperty("timeStampInMs") long timeStampInMs) implements OrderbookData {

    public HerronOrderbookData(OrderbookData orderbookData) {
        this(orderbookData.orderbookId(),
                orderbookData.instrumentId(),
                orderbookData.matchingAlgorithm(),
                orderbookData.timeStampInMs());
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_ORDERBOOK_DATA;
    }

}
