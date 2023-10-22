package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.orders.LimitOrder;
import com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum.DEFAULT_LIMIT_ORDER;

@Value.Immutable
@JsonDeserialize(builder = ImmutableDefaultLimitOrder.Builder.class)
public interface DefaultLimitOrder extends LimitOrder {

    @Value.Derived
    default TradingMessageTypeEnum messageType() {
        return DEFAULT_LIMIT_ORDER;
    }
}
