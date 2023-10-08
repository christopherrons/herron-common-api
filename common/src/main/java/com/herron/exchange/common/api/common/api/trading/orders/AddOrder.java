package com.herron.exchange.common.api.common.api.trading.orders;

import com.herron.exchange.common.api.common.enums.OrderAddOperationTypeEnum;

public interface AddOrder extends Order {
    OrderAddOperationTypeEnum addOperationType();
}
