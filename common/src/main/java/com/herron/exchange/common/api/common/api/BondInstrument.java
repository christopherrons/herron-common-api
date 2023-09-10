package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.CompoundingMethodEnum;

import java.time.LocalDate;

public interface BondInstrument extends Instrument {

    double nominalValue();

    int couponYearlyFrequency();

    LocalDate maturityDate();

    LocalDate startDate();

    CompoundingMethodEnum compoundingMethod();

    double couponRate();

}
