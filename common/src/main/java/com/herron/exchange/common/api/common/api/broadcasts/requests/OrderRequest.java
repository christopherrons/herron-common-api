package com.herron.exchange.common.api.common.api.broadcasts.requests;

import com.herron.exchange.common.api.common.api.trading.orders.Order;

public interface OrderRequest extends Request {

    Order order();
}
