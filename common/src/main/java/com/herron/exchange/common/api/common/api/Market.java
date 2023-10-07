package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.model.HerronBusinessCalendar;

public interface Market extends Message {

    String marketId();

    HerronBusinessCalendar businessCalendar();
}
