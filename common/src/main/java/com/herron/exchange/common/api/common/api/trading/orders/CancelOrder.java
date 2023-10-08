package com.herron.exchange.common.api.common.api.trading.orders;

import com.herron.exchange.common.api.common.enums.OrderCancelOperationTypeEnum;

public interface CancelOrder extends Order {
    OrderCancelOperationTypeEnum cancelOperationType();
}
