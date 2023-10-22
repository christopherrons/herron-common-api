package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.OrderbookEvent;
import com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum;
import org.immutables.value.Value;

import java.util.List;

import static com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum.TRADE_EXECUTION;


@Value.Immutable
@JsonDeserialize(builder = ImmutableTradeExecution.Builder.class)
public interface TradeExecution extends OrderbookEvent {
    List<OrderbookEvent> messages();

    @Value.Derived
    default TradingMessageTypeEnum messageType() {
        return TRADE_EXECUTION;
    }
}
