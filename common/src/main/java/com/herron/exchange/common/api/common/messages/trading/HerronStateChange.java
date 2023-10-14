package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.statechange.StateChange;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableHerronStateChange.Builder.class)
public interface HerronStateChange extends StateChange {

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_ORDERBOOK_STATE_CHANGE;
    }

}
