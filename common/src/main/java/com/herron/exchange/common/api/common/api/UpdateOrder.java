package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.OrderUpdatedOperationTypeEnum;

public interface UpdateOrder extends Order {
    OrderUpdatedOperationTypeEnum updateOperationType();
}
