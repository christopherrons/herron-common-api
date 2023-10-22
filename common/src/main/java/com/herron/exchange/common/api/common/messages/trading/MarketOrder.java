package com.herron.exchange.common.api.common.messages.trading;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.trading.Order;
import com.herron.exchange.common.api.common.enums.OrderTypeEnum;
import com.herron.exchange.common.api.common.enums.TimeInForceEnum;
import com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum;
import com.herron.exchange.common.api.common.messages.common.Price;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.OrderTypeEnum.MARKET;
import static com.herron.exchange.common.api.common.enums.TimeInForceEnum.FAK;
import static com.herron.exchange.common.api.common.enums.messagetypes.TradingMessageTypeEnum.MARKET_ORDER;
import static java.lang.Integer.MAX_VALUE;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMarketOrder.Builder.class)
public interface MarketOrder extends Order {
    @Value.Derived
    default OrderTypeEnum orderType() {
        return MARKET;
    }

    @Value.Derived
    default Price price() {
        return Price.create(MAX_VALUE);
    }

    @Value.Derived
    default TimeInForceEnum timeInForce() {
        return FAK;
    }

    @Value.Derived
    default TradingMessageTypeEnum messageType() {
        return MARKET_ORDER;
    }

}
