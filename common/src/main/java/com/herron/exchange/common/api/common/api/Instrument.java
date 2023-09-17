package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.InstrumentTypeEnum;

public interface Instrument extends Message {

    String instrumentId();

    InstrumentTypeEnum instrumentType();

    String marketId();

    String currency();
}
