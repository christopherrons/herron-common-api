package com.herron.exchange.common.api.common.api.trading.orders;

import com.herron.exchange.common.api.common.enums.OrderAddOperationTypeEnum;
import com.herron.exchange.common.api.common.enums.OrderOperationEnum;
import org.immutables.value.Value;

public interface AddOrder extends Order {
    OrderAddOperationTypeEnum addOperationType();

    @Value.Derived
    default OrderOperationEnum orderOperation() {
        return OrderOperationEnum.INSERT;
    }
}
