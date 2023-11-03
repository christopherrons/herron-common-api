package com.herron.exchange.common.api.common.model;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.MonetaryAmount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AmountTest {

    private final double thisRealAmount = 2.0;
    private final double thatRealAmount = 10.0;
    private final MonetaryAmount thisAmount = MonetaryAmount.create(thisRealAmount, "eur");
    private final MonetaryAmount thatAmount = MonetaryAmount.create(thatRealAmount, "eur");
    private final MessageFactory messageFactory = new DefaultMessageFactory();

    @Test
    void test_serialization_and_deserialization() {
        var object = MonetaryAmount.create(100, "eur");
        var value = messageFactory.serialize(object);
        assertNotNull(value);
        assertNotNull(messageFactory.deserializeMessage(value));
    }

    @Test
    void test_gt() {
        assertTrue(thatAmount.gt(thisAmount));
        assertTrue(thatAmount.gt(thisRealAmount));
    }

    @Test
    void test_lt() {
        assertTrue(thisAmount.lt(thatAmount));
        assertTrue(thisAmount.lt(thatRealAmount));
    }

    @Test
    void test_ge() {
        assertTrue(thatAmount.geq(thisAmount));
        assertTrue(thatAmount.geq(thisRealAmount));
    }

    @Test
    void test_le() {
        assertTrue(thisAmount.leq(thatAmount));
        assertTrue(thisAmount.leq(thatRealAmount));
    }

    @Test
    void test_add() {
        assertEquals(MonetaryAmount.create(12.0, "eur"), thisAmount.add(thatAmount));
        assertEquals(MonetaryAmount.create(12.0, "eur"), thisAmount.add(thatRealAmount));
        assertEquals(MonetaryAmount.create(12.0, "eur"), thisAmount.add(BigDecimal.valueOf(thatRealAmount)));
    }

    @Test
    void test_subtract() {
        assertEquals(MonetaryAmount.create(-8.0, "eur"), thisAmount.subtract(thatAmount));
        assertEquals(MonetaryAmount.create(-8.0, "eur"), thisAmount.subtract(thatRealAmount));
        assertEquals(MonetaryAmount.create(-8.0, "eur"), thisAmount.subtract(BigDecimal.valueOf(thatRealAmount)));
    }

    @Test
    void test_multiply() {
        assertEquals(MonetaryAmount.create(20.0, "eur").scale(2), thisAmount.multiply(thatAmount));
        assertEquals(MonetaryAmount.create(20.0, "eur").scale(2), thisAmount.multiply(thatRealAmount));
        assertEquals(MonetaryAmount.create(20.0, "eur").scale(2), thisAmount.multiply(BigDecimal.valueOf(thatRealAmount)));
    }

    @Test
    void test_divide() {
        assertEquals(MonetaryAmount.create(5.0, "eur"), thatAmount.divide(thisAmount).scale(1));
        assertEquals(MonetaryAmount.create(5.0, "eur"), thatAmount.divide(thisRealAmount).scale(1));
        assertEquals(MonetaryAmount.create(5.0, "eur"), thatAmount.divide(BigDecimal.valueOf(thisRealAmount)).scale(1));
    }


    @Test
    void test_percentageChange() {
        assertEquals(0.8, thatAmount.percentageChange(thisAmount));
        assertEquals(0.8, thatAmount.percentageChange(thisRealAmount));
        assertEquals(0.8, thatAmount.percentageChange(BigDecimal.valueOf(thisRealAmount)));

        assertEquals(4, thisAmount.percentageChange(thatAmount));
        assertEquals(4, thisAmount.percentageChange(thatRealAmount));
        assertEquals(4, thisAmount.percentageChange(BigDecimal.valueOf(thatRealAmount)));
    }
}