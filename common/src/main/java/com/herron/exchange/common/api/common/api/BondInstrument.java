package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.CompoundingMethodEnum;
import com.herron.exchange.common.api.common.enums.DayCountConvetionEnum;

import java.time.LocalDate;

public interface BondInstrument extends Instrument {

    double nominalValue();

    int couponAnnualFrequency();

    LocalDate maturityDate();

    LocalDate startDate();

    CompoundingMethodEnum compoundingMethod();

    double couponRate();

    DayCountConvetionEnum dayCountConvention();

}
