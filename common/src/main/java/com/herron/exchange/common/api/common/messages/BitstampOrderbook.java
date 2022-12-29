package com.herron.exchange.common.api.common.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.api.Orderbook;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BitstampOrderbook(@JsonProperty("orderbookId") String orderbookId,
                                @JsonProperty("instrumentId") String instrumentId,
                                @JsonProperty("timeStampInMs") long timeStampInMs) implements Orderbook {

    @Override
    public MessageTypesEnum getMessageType() {
        return MessageTypesEnum.BITSTAMP_ORDERBOOK;
    }

    @Override
    public long getTimeStampMs() {
        return timeStampInMs;
    }
}
