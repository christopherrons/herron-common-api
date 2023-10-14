package com.herron.exchange.common.api.common.api.trading.trades;

import com.herron.exchange.common.api.common.api.Event;
import com.herron.exchange.common.api.common.model.Participant;
import com.herron.exchange.common.api.common.model.Price;
import com.herron.exchange.common.api.common.model.Volume;

public interface Trade extends Event {


    Participant bidParticipant();

    Participant askParticipant();

    String tradeId();

    String buyOrderId();

    String askOrderId();

    boolean isBidSideAggressor();

    Volume volume();

    Price price();

    String instrumentId();

    String orderbookId();

}
