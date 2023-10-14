package com.herron.exchange.common.api.common.api.trading.orders;

import com.herron.exchange.common.api.common.enums.OrderOperationEnum;
import com.herron.exchange.common.api.common.enums.OrderUpdatedOperationTypeEnum;
import org.immutables.value.Value;

public interface UpdateOrder extends Order {
    OrderUpdatedOperationTypeEnum updateOperationType();

    @Value.Default
    default OrderOperationEnum orderOperation() {
        return OrderOperationEnum.UPDATE;
    }
}
