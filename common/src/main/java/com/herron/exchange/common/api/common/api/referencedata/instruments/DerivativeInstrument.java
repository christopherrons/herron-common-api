package com.herron.exchange.common.api.common.api.referencedata.instruments;

import com.herron.exchange.common.api.common.enums.SettlementTypeEnum;

import java.time.LocalDate;

public interface DerivativeInstrument extends Instrument {

    LocalDate maturityDate();

    String underlyingInstrumentId();

    SettlementTypeEnum settlementType();
}
