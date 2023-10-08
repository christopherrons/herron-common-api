package com.herron.exchange.common.api.common.api.referencedata.instruments;

import com.herron.exchange.common.api.common.enums.CompoundingMethodEnum;
import com.herron.exchange.common.api.common.enums.DayCountConvetionEnum;
import com.herron.exchange.common.api.common.enums.InstrumentTypeEnum;
import org.immutables.value.Value;

import java.time.LocalDate;

public interface BondInstrument extends Instrument {

    double nominalValue();

    int couponAnnualFrequency();

    LocalDate maturityDate();

    LocalDate startDate();

    CompoundingMethodEnum compoundingMethod();

    double couponRate();

    DayCountConvetionEnum dayCountConvention();

    @Value.Default
    default boolean isZeroCouponBond() {
        return couponRate() == 0;
    }

    @Value.Default
    default InstrumentTypeEnum instrumentType() {
        return InstrumentTypeEnum.BOND;
    }

}
