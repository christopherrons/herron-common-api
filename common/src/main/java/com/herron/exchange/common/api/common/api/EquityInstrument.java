package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.InstrumentTypeEnum;

public interface EquityInstrument extends Instrument {

    default InstrumentTypeEnum instrumentType() {
        return InstrumentTypeEnum.STOCK;
    }
}
