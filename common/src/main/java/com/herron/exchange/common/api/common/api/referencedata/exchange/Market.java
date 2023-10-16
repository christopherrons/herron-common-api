package com.herron.exchange.common.api.common.api.referencedata.exchange;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.messages.refdata.ImmutableDefaultMarket;


public interface Market extends Message {

    String marketId();

    BusinessCalendar businessCalendar();
}
