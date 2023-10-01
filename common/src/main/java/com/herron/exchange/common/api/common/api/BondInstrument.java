package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.CompoundingMethodEnum;
import com.herron.exchange.common.api.common.enums.DayCountConvetionEnum;
import com.herron.exchange.common.api.common.enums.InstrumentTypeEnum;

import java.time.LocalDate;

public interface BondInstrument extends Instrument {

    double nominalValue();

    int couponAnnualFrequency();

    LocalDate maturityDate();

    LocalDate startDate();

    CompoundingMethodEnum compoundingMethod();

    double couponRate();

    DayCountConvetionEnum dayCountConvention();

    default boolean isZeroCouponBond() {
        return couponRate() == 0;
    }

    default InstrumentTypeEnum instrumentType() {
        return InstrumentTypeEnum.BOND;
    }

}
