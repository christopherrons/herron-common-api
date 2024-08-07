package com.herron.exchange.common.api.common.messages.refdata;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.enums.OptionExerciseTyleEnum;
import com.herron.exchange.common.api.common.enums.OptionSubTypeEnum;
import com.herron.exchange.common.api.common.enums.OptionTypeEnum;
import com.herron.exchange.common.api.common.enums.SettlementTypeEnum;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.BusinessCalendar;
import com.herron.exchange.common.api.common.messages.common.PureNumber;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import com.herron.exchange.common.api.common.messages.pricing.ImmutableBlackScholesPriceModelParameters;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultOptionInstrumentTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = ImmutableDefaultOptionInstrument.builder()
                .instrumentId("instrumendId")
                .underlyingInstrumentId("underlying")
                .settlementType(SettlementTypeEnum.PHYSICAL)
                .firstTradingDate(Timestamp.from(LocalDate.MIN))
                .lastTradingDate(Timestamp.from(LocalDate.MAX))
                .maturityDate(Timestamp.now())
                .strikePrice(PureNumber.create(100))
                .optionSubType(OptionSubTypeEnum.OOE)
                .optionType(OptionTypeEnum.CALL)
                .optionExerciseStyle(OptionExerciseTyleEnum.AMERICAN)
                .priceModelParameters(ImmutableBlackScholesPriceModelParameters.builder().yieldCurveId("id").build())
                .product(ImmutableProduct.builder().currency("eur").productId("product").market(ImmutableMarket.builder().marketId("market").businessCalendar(BusinessCalendar.defaultWeekendCalendar()).build()).build())
                .build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
        assertEquals(object, messageFactory.deserializeMessage(value));
    }
}