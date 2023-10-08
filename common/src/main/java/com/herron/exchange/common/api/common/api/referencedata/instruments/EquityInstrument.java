package com.herron.exchange.common.api.common.api.referencedata.instruments;

import com.herron.exchange.common.api.common.enums.InstrumentTypeEnum;

public interface EquityInstrument extends Instrument {

    default InstrumentTypeEnum instrumentType() {
        return InstrumentTypeEnum.STOCK;
    }
}
