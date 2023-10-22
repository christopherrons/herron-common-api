package com.herron.exchange.common.api.common.messages.refdata;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.SettlementTypeEnum;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.DefaultBusinessCalendar;
import com.herron.exchange.common.api.common.messages.pricemodel.ImmutableBasicFuturePriceModelParameters;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultFutureInstrumentTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = ImmutableDefaultFutureInstrument.builder()
                .instrumentId("instrumendId")
                .underlyingInstrumentId("underlying")
                .settlementType(SettlementTypeEnum.PHYSICAL)
                .firstTradingDate(LocalDate.MIN)
                .lastTradingDate(LocalDate.MAX)
                .maturityDate(LocalDate.now())
                .priceModelParameters(ImmutableBasicFuturePriceModelParameters.builder().build())
                .product(ImmutableDefaultProduct.builder().currency("eur").productId("product").market(ImmutableDefaultMarket.builder().marketId("market").businessCalendar(DefaultBusinessCalendar.defaultWeekendCalendar()).build()).build())
                .build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }
}