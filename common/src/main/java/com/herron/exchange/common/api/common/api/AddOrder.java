package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.OrderAddOperationTypeEnum;

public interface AddOrder extends Order {
    OrderAddOperationTypeEnum addOperationType();
}
