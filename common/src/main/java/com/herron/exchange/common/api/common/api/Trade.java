package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.model.MonetaryAmount;
import com.herron.exchange.common.api.common.model.Participant;

public interface Trade extends Message {


    Participant bidParticipant();

    Participant askParticipant();

    String tradeId();

    String buyOrderId();

    String askOrderId();

    boolean isBidSideAggressor();

    double volume();
    double price();

    MonetaryAmount monetaryAmount();

    long timeStampInMs();

    String instrumentId();

    String orderbookId();

}
