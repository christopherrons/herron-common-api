package com.herron.exchange.common.api.common.api.trading.orders;

import com.herron.exchange.common.api.common.enums.OrderTypeEnum;
import org.immutables.value.Value;

import static com.herron.exchange.common.api.common.enums.OrderTypeEnum.LIMIT;

public interface LimitOrder extends Order {

    @Value.Derived
    default OrderTypeEnum orderType() {
        return LIMIT;
    }
}
