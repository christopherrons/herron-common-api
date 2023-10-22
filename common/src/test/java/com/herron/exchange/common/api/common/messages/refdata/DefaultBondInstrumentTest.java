package com.herron.exchange.common.api.common.messages.refdata;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.CompoundingMethodEnum;
import com.herron.exchange.common.api.common.enums.DayCountConventionEnum;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.BusinessCalendar;
import com.herron.exchange.common.api.common.messages.common.MonetaryAmount;
import com.herron.exchange.common.api.common.messages.pricing.ImmutableBondDiscountPriceModelParameters;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultBondInstrumentTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = ImmutableDefaultBondInstrument.builder()
                .instrumentId("instrumendId")
                .couponAnnualFrequency(1)
                .firstTradingDate(LocalDate.MIN)
                .lastTradingDate(LocalDate.MAX)
                .nominalValue(MonetaryAmount.create(1, "eur"))
                .maturityDate(LocalDate.now())
                .couponRate(1)
                .priceModelParameters(ImmutableBondDiscountPriceModelParameters.builder().compoundingMethod(CompoundingMethodEnum.COMPOUNDING)
                        .dayCountConvention(DayCountConventionEnum.ACT365)
                        .calculateWithCurve(true)
                        .yieldCurveId("id")
                        .build()
                )
                .product(ImmutableProduct.builder().currency("eur").productId("product").market(ImmutableMarket.builder().marketId("market").businessCalendar(BusinessCalendar.defaultWeekendCalendar()).build()).build())
                .build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }
}