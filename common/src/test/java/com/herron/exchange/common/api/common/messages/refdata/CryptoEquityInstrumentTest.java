package com.herron.exchange.common.api.common.messages.refdata;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.BusinessCalendar;
import com.herron.exchange.common.api.common.messages.common.Timestamp;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CryptoEquityInstrumentTest {
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = ImmutableCryptoEquityInstrument.builder()
                .instrumentId("instrumentId")
                .token("BTC")
                .counterCurrency("USD")
                .firstTradingDate(Timestamp.from(LocalDate.MIN))
                .lastTradingDate(Timestamp.from(LocalDate.MAX))
                .priceModelParameters(ImmutableIntangiblePriceModelParameters.builder().build())
                .product(ImmutableProduct.builder().currency("eur").productId("product").market(ImmutableMarket.builder().marketId("market").businessCalendar(BusinessCalendar.defaultWeekendCalendar()).build()).build())
                .build();
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
        assertEquals(object, messageFactory.deserializeMessage(value));
    }
}