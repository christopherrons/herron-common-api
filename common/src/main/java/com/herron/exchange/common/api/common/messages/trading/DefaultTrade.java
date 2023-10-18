package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.trades.Trade;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.MessageTypesEnum.DEFAULT_TRADE;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultTrade.Builder.class)
public interface DefaultTrade extends Trade {

    @Value.Derived
    default MessageTypesEnum messageType() {
        return DEFAULT_TRADE;
    }

}
