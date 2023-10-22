package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.Order;
import com.herron.exchange.common.api.common.enums.OrderTypeEnum;
import com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.OrderTypeEnum.LIMIT;
import static com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum.LIMIT_ORDER;

@Value.Immutable
@JsonDeserialize(builder = ImmutableLimitOrder.Builder.class)
public interface LimitOrder extends Order {
    @Value.Derived
    default OrderTypeEnum orderType() {
        return LIMIT;
    }

    @Value.Derived
    default TradingMessageTypeEnum messageType() {
        return LIMIT_ORDER;
    }
}
