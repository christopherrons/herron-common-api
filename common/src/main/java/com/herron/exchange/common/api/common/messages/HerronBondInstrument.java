package com.herron.exchange.common.api.common.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.BondInstrument;
import com.herron.exchange.common.api.common.enums.CompoundingMethodEnum;
import com.herron.exchange.common.api.common.enums.DayCountConvetionEnum;
import com.herron.exchange.common.api.common.enums.InstrumentTypeEnum;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronBondInstrument(String instrumentId,
                                   int couponAnnualFrequency,
                                   LocalDate maturityDate,
                                   LocalDate startDate,
                                   double nominalValue,
                                   CompoundingMethodEnum compoundingMethod,
                                   double couponRate,
                                   DayCountConvetionEnum dayCountConvention) implements BondInstrument {

    public HerronBondInstrument(BondInstrument instrument) {
        this(instrument.instrumentId(),
                instrument.couponAnnualFrequency(),
                instrument.maturityDate(),
                instrument.startDate(),
                instrument.nominalValue(),
                instrument.compoundingMethod(),
                instrument.couponRate(),
                instrument.dayCountConvention());
    }

    @Override
    public HerronBondInstrument getCopy() {
        return new HerronBondInstrument(this);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_BOND_INSTRUMENT;
    }

    @Override
    public InstrumentTypeEnum instrumentType() {
        return InstrumentTypeEnum.BOND;
    }
}
