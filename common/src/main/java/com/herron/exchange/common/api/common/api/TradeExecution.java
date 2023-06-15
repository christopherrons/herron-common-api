package com.herron.exchange.common.api.common.api;

import java.util.List;

public interface TradeExecution extends Message {

    List<Message> messages();

}
