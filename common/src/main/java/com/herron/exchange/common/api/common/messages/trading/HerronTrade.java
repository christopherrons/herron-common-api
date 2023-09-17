package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.Trade;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.model.MonetaryAmount;
import com.herron.exchange.common.api.common.model.Participant;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronTrade(Participant bidParticipant,
                          Participant askParticipant,
                          String tradeId,
                          String buyOrderId,
                          String askOrderId,
                          boolean isBidSideAggressor,
                          double volume,
                          double price,
                          long timeStampInMs,
                          String instrumentId,
                          String orderbookId) implements Trade {


    public HerronTrade(Trade trade) {
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
    public HerronTrade getCopy() {
        return new HerronTrade(this);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_TRADE;
    }

}
