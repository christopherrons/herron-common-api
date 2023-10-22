package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.OrderbookEvent;
import com.herron.exchange.common.api.common.enums.TradingStatesEnum;
import com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum.STATE_CHANGE;


@Value.Immutable
@JsonDeserialize(builder = ImmutableStateChange.Builder.class)
public interface StateChange extends OrderbookEvent {
    TradingStatesEnum tradeState();

    @Value.Derived
    default TradingMessageTypeEnum messageType() {
        return STATE_CHANGE;
    }
}
