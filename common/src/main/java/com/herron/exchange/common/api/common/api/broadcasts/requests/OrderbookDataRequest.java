package com.herron.exchange.common.api.common.api.broadcasts.requests;

import com.herron.exchange.common.api.common.api.referencedata.orderbook.OrderbookData;

public interface OrderbookDataRequest extends Request {

    OrderbookData orderbookData();

}
