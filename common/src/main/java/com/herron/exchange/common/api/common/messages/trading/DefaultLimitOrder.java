package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.orders.LimitOrder;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultLimitOrder.Builder.class)
public interface DefaultLimitOrder extends LimitOrder {

    @Value.Default
    default MessageTypesEnum messageType() {
        return MessageTypesEnum.DEFAULT_LIMIT_ORDER;
    }
}
