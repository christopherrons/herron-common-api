package com.herron.exchange.common.api.common.api.trading.trades;

import com.herron.exchange.common.api.common.api.Event;
import com.herron.exchange.common.api.common.api.Message;

import java.util.List;

public interface TradeExecution extends Event {

    Message triggerMessage();

    List<Message> messages();

}
