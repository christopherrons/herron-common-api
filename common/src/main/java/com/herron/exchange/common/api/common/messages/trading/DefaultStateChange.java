package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.statechange.StateChange;
import com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum.DEFAULT_STATE_CHANGE;


@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultStateChange.Builder.class)
public interface DefaultStateChange extends StateChange {

    @Value.Derived
    default TradingMessageTypeEnum messageType() {
        return DEFAULT_STATE_CHANGE;
    }
}
