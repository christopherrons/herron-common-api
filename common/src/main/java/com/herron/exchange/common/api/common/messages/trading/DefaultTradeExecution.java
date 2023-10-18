package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.trades.TradeExecution;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.MessageTypesEnum.DEFAULT_TRADE_EXECUTION;


@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultTradeExecution.Builder.class)
public interface DefaultTradeExecution extends TradeExecution {

    @Value.Derived
    default MessageTypesEnum messageType() {
        return DEFAULT_TRADE_EXECUTION;
    }
}
