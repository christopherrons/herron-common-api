package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.InstrumentTypeEnum;
import com.herron.exchange.common.api.common.model.Market;

public interface Instrument extends Message {

    String instrumentId();

    InstrumentTypeEnum instrumentType();

    Market market();
}
