package com.herron.exchange.common.api.common.api.referencedata.exchange;

import com.herron.exchange.common.api.common.api.Message;

public interface Market extends Message {

    String marketId();

    BusinessCalendar businessCalendar();
}
