package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.OrderCancelOperationTypeEnum;

public interface CancelOrder extends Order {
    OrderCancelOperationTypeEnum cancelOperationType();
}
