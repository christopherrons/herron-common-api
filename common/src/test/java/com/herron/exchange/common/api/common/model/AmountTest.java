package com.herron.exchange.common.api.common.model;

import com.herron.exchange.common.api.common.api.MessageFactory;
import com.herron.exchange.common.api.common.mapping.DefaultMessageFactory;
import com.herron.exchange.common.api.common.messages.common.MonetaryAmount;
import org.junit.jupiter.api.Test;

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
    }

    @Test
    void test_subtract() {
        assertEquals(MonetaryAmount.create(-8.0, "eur"), thisAmount.subtract(thatAmount));
        assertEquals(MonetaryAmount.create(-8.0, "eur"), thisAmount.subtract(thatRealAmount));
    }

    @Test
    void test_multiply() {
        assertEquals(MonetaryAmount.create(20.0, "eur").scale(2), thisAmount.multiply(thatAmount));
        assertEquals(MonetaryAmount.create(20.0, "eur").scale(2), thisAmount.multiply(thatRealAmount));
    }

    @Test
    void test_divide() {
        assertEquals(MonetaryAmount.create(5.0, "eur"), thatAmount.divide(thisAmount));
        assertEquals(MonetaryAmount.create(5.0, "eur"), thatAmount.divide(thisRealAmount));
    }
}