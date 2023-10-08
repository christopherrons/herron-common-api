package com.herron.exchange.common.api.common.messages.trading;

import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.api.trading.trades.TradeExecution;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

import java.util.List;

public record HerronTradeExecution(Message triggerMessage, List<Message> messages, long timeStampInMs) implements TradeExecution {

    public HerronTradeExecution(TradeExecution tradeExecution) {
        this(tradeExecution.triggerMessage(), tradeExecution.messages(), tradeExecution.timeStampInMs());
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_TRADE_EXECUTION;
    }
}
