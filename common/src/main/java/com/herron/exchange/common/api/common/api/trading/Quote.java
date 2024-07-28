package com.herron.exchange.common.api.common.api.trading;

import com.herron.exchange.common.api.common.enums.QuoteTypeEnum;

public interface Quote extends OrderbookEvent {

    QuoteTypeEnum quoteType();

}
