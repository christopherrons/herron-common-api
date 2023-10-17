package com.herron.exchange.common.api.common.api.trading.trades;

import com.herron.exchange.common.api.common.api.Event;
import com.herron.exchange.common.api.common.messages.common.Participant;
import com.herron.exchange.common.api.common.messages.common.Price;
import com.herron.exchange.common.api.common.messages.common.Volume;

public interface Trade extends Event {

    Participant bidParticipant();

    Participant askParticipant();

    String tradeId();

    String bidOrderId();

    String askOrderId();

    boolean isBidSideAggressor();

    Volume volume();

    Price price();

    String instrumentId();

    String orderbookId();

}
