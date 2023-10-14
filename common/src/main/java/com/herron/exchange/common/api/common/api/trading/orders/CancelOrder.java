package com.herron.exchange.common.api.common.api.trading.orders;

import com.herron.exchange.common.api.common.enums.OrderCancelOperationTypeEnum;
import com.herron.exchange.common.api.common.enums.OrderOperationEnum;
import org.immutables.value.Value;

public interface CancelOrder extends Order {
    OrderCancelOperationTypeEnum cancelOperationType();

    @Value.Default
    default OrderOperationEnum orderOperation() {
        return OrderOperationEnum.DELETE;
    }
}
