package com.herron.exchange.common.api.common.messages.bitstamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.api.OrderbookData;
import com.herron.exchange.common.api.common.enums.MatchingAlgorithmEnum;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BitstampOrderbookData(@JsonProperty("orderbookId") String orderbookId,
                                    @JsonProperty("instrumentId") String instrumentId,
                                    @JsonProperty("matchingAlgorithm") String matchingAlgorithmString,
                                    @JsonProperty("timeStampInMs") long timeStampInMs) implements OrderbookData {

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.BITSTAMP_ORDERBOOK;
    }

    @Override
    public MatchingAlgorithmEnum matchingAlgorithm() {
        return MatchingAlgorithmEnum.fromValue(matchingAlgorithmString);
    }
}
