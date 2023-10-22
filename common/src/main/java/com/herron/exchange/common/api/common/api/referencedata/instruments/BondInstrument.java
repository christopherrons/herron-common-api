package com.herron.exchange.common.api.common.api.referencedata.instruments;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.herron.exchange.common.api.common.enums.InstrumentTypeEnum;
import com.herron.exchange.common.api.common.messages.pricemodel.BondDiscountPriceModelParameters;
import org.immutables.value.Value;

import java.time.LocalDate;

public interface BondInstrument extends Instrument {

    double nominalValue();

    int couponAnnualFrequency();

    LocalDate maturityDate();

    @Value.Default
    default LocalDate startDate() {
        return firstTradingDate();
    }

    double couponRate();

    @Value.Default
    @JsonIgnore
    default boolean isZeroCouponBond() {
        return couponRate() == 0;
    }

    @Value.Default
    default InstrumentTypeEnum instrumentType() {
        return InstrumentTypeEnum.BOND;
    }

    @Override
    BondDiscountPriceModelParameters priceModelParameters();

}
