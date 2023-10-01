package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.InstrumentTypeEnum;
import org.immutables.value.Value;

import java.time.LocalDate;

public interface Instrument extends Message {

    String instrumentId();

    InstrumentTypeEnum instrumentType();

    String marketId();

    String currency();

    @Value.Default
    default double contractSize() {
        return 1;
    }

    LocalDate firstTradingDate();

    LocalDate lastTradingDate();

}
