package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.trades.Trade;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableHerronTrade.Builder.class)
public interface HerronTrade extends Trade {

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_TRADE;
    }

}
