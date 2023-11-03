package com.herron.exchange.common.api.common.api.referencedata.instruments;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.herron.exchange.common.api.common.enums.InstrumentTypeEnum;
import com.herron.exchange.common.api.common.messages.common.MonetaryAmount;
import com.herron.exchange.common.api.common.messages.common.PureNumber;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import com.herron.exchange.common.api.common.messages.pricing.BondDiscountPriceModelParameters;
import org.immutables.value.Value;

public interface BondInstrument extends Instrument {

    MonetaryAmount nominalValue();

    int couponAnnualFrequency();

    Timestamp maturityDate();

    @Value.Default
    default Timestamp startDate() {
        return firstTradingDate();
    }

    PureNumber couponRate();

    @Value.Default
    @JsonIgnore
    default boolean isZeroCouponBond() {
        return couponRate() == PureNumber.ZERO;
    }

    @Value.Default
    default InstrumentTypeEnum instrumentType() {
        return InstrumentTypeEnum.BOND;
    }

    @Override
    BondDiscountPriceModelParameters priceModelParameters();

    @Value.Check
    default void checkCurrency() {
        if (!nominalValue().getCurrency().equals(currency())) {
            throw new IllegalArgumentException(String.format("Nominal value currency %s does not match instrument currency %s", nominalValue().getCurrency(), currency()));
        }
    }

}
