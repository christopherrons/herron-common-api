package com.herron.exchange.common.api.common.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.api.Trade;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.model.Participant;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BitstampTrade(@JsonProperty("bidParticipant") Participant bidParticipant,
                            @JsonProperty("askParticipant") Participant askParticipant,
                            @JsonProperty("tradeId") long tradeId,
                            @JsonProperty("buyOrderId") String buyOrderId,
                            @JsonProperty("askOrderId") String askOrderId,
                            @JsonProperty("isBidSideAggressor") boolean isBidSideAggressor,
                            @JsonProperty("volume") double volume,
                            @JsonProperty("price") double price,
                            @JsonProperty("timeStampInMs") long timeStampInMs,
                            @JsonProperty("instrumentId") String instrumentId,
                            @JsonProperty("orderbookId") String orderbookId) implements Trade {


    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.BITSTAMP_TRADE;
    }

}
