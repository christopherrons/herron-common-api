package com.herron.exchange.common.api.common.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.herron.exchange.common.api.common.api.TradingCalendar;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableHerronTradingCalendar.Builder.class)
public interface HerronTradingCalendar extends TradingCalendar {

}
