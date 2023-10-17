package com.herron.exchange.common.api.common.messages.refdata;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.CompoundingMethodEnum;
import com.herron.exchange.common.api.common.enums.DayCountConvetionEnum;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.DefaultBusinessCalendar;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultBondInstrumentTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = ImmutableDefaultBondInstrument.builder()
                .instrumentId("instrumendId")
                .compoundingMethod(CompoundingMethodEnum.COMPOUNDING)
                .dayCountConvention(DayCountConvetionEnum.ACT365)
                .couponAnnualFrequency(1)
                .firstTradingDate(LocalDate.MIN)
                .lastTradingDate(LocalDate.MAX)
                .nominalValue(1)
                .maturityDate(LocalDate.now())
                .couponRate(1)
                .product(ImmutableDefaultProduct.builder().currency("eur").productId("product").market(ImmutableDefaultMarket.builder().marketId("market").businessCalendar(DefaultBusinessCalendar.defaultWeekendCalendar()).build()).build())
                .build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }
}