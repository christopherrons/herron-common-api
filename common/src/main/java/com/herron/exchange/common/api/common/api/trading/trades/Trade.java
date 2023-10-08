package com.herron.exchange.common.api.common.api.trading.trades;

import com.herron.exchange.common.api.common.api.Event;
import com.herron.exchange.common.api.common.model.Participant;

public interface Trade extends Event {


    Participant bidParticipant();

    Participant askParticipant();

    String tradeId();

    String buyOrderId();

    String askOrderId();

    boolean isBidSideAggressor();

    double volume();

    double price();

    long timeStampInMs();

    String instrumentId();

    String orderbookId();

}
