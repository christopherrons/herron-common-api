package com.herron.exchange.common.api.common.api.trading.orders;

import com.herron.exchange.common.api.common.enums.OrderTypeEnum;
import com.herron.exchange.common.api.common.enums.TimeInForceEnum;
import com.herron.exchange.common.api.common.messages.common.Price;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.OrderTypeEnum.MARKET;
import static com.herron.exchange.common.api.common.enums.TimeInForceEnum.FAK;
import static java.lang.Integer.MAX_VALUE;

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

}
