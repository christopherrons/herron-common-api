package com.herron.exchange.common.api.common.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.BondInstrument;
import com.herron.exchange.common.api.common.enums.CompoundingMethodEnum;
import com.herron.exchange.common.api.common.enums.InstrumentTypeEnum;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronBondInstrument(String instrumentId,
                                   InstrumentTypeEnum instrumentType,
                                   int couponYearlyFrequency,
                                   LocalDate maturityDate,
                                   double nominalValue,
                                   CompoundingMethodEnum compoundingMethod) implements BondInstrument {

    public HerronBondInstrument(BondInstrument instrument) {
        this(instrument.instrumentId(),
                instrument.instrumentType(),
                instrument.couponYearlyFrequency(),
                instrument.maturityDate(),
                instrument.nominalValue(),
                instrument.compoundingMethod());
    }

    @Override
    public HerronBondInstrument getCopy() {
        return new HerronBondInstrument(this);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_BOND_INSTRUMENT;
    }

}
