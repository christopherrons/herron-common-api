package com.herron.exchange.common.api.common.messages.bitstamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.api.Trade;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.model.MonetaryAmount;
import com.herron.exchange.common.api.common.model.Participant;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BitstampTrade(@JsonProperty("bidParticipant") Participant bidParticipant,
                            @JsonProperty("askParticipant") Participant askParticipant,
                            @JsonProperty("tradeId") String tradeId,
                            @JsonProperty("buyOrderId") String buyOrderId,
                            @JsonProperty("askOrderId") String askOrderId,
                            @JsonProperty("isBidSideAggressor") boolean isBidSideAggressor,
                            @JsonProperty("volume") double volume,
                            @JsonProperty("price") MonetaryAmount price,
                            @JsonProperty("timeStampInMs") long timeStampInMs,
                            @JsonProperty("instrumentId") String instrumentId,
                            @JsonProperty("orderbookId") String orderbookId) implements Trade {

    public BitstampTrade(Trade trade) {
        this(trade.bidParticipant(),
                trade.askParticipant(),
                trade.tradeId(),
                trade.buyOrderId(),
                trade.askOrderId(),
                trade.isBidSideAggressor(),
                trade.volume(),
                trade.price(),
                trade.timeStampInMs(),
                trade.instrumentId(),
                trade.orderbookId());
    }

    @Override
    public BitstampTrade getCopy() {
        return new BitstampTrade(this);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.BITSTAMP_TRADE;
    }

}
