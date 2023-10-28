package com.herron.exchange.common.api.common.api.referencedata.instruments;

import com.herron.exchange.common.api.common.enums.SettlementTypeEnum;
import com.herron.exchange.common.api.common.messages.common.Timestamp;

public interface DerivativeInstrument extends Instrument {

    Timestamp maturityDate();

    String underlyingInstrumentId();

    SettlementTypeEnum settlementType();
}
