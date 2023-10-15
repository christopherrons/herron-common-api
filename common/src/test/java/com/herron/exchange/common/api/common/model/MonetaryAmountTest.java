package com.herron.exchange.common.api.common.model;

import com.herron.exchange.common.api.common.messages.common.MonetaryAmount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MonetaryAmountTest {

    private final MonetaryAmount thisAmount = MonetaryAmount.create(2.0, "eur");
    private final MonetaryAmount thatAmount = MonetaryAmount.create(10.0, "eur");

    @Test
    void test_gt() {
        assertTrue(thatAmount.gt(thisAmount));
    }

    @Test
    void test_lt() {
        assertTrue(thisAmount.lt(thatAmount));
    }

    @Test
    void test_ge() {
        assertTrue(thatAmount.ge(thisAmount));
    }

    @Test
    void test_le() {
        assertTrue(thisAmount.le(thatAmount));
    }

    @Test
    void test_add() {
        assertEquals(MonetaryAmount.create(12.0, "eur"), thisAmount.add(thatAmount));
    }

    @Test
    void test_subtract() {
        assertEquals(MonetaryAmount.create(-8.0, "eur"), thisAmount.subtract(thatAmount));
    }

    @Test
    void test_multiply() {
        assertEquals(MonetaryAmount.create(20.0, "eur"), thisAmount.multiply(thatAmount));
    }

    @Test
    void test_divide() {
        assertEquals(MonetaryAmount.create(5.0, "eur"), thatAmount.divide(thisAmount));
    }
}