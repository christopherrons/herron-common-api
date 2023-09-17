package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.model.BusinessCalendar;

public interface Market extends Message {

    String marketId();

    BusinessCalendar businessCalendar();
}
