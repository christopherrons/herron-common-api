package com.herron.exchange.common.api.common.enums;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DayCountConvetionEnumTest {

    @Test
    void test_bond_basis_30360() {
        var convention = DayCountConvetionEnum.BOND_BASIS_30360;
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endEnd = LocalDate.of(2023, 2, 28);
        assertEquals(1 / 6.0, convention.calculateDayCountFraction(startDate, endEnd));
    }

    @Test
    void test_bond_basis_act_365() {
        var convention = DayCountConvetionEnum.ACT365;
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endEnd = LocalDate.of(2023, 2, 28);
        assertEquals(59 / 365.0, convention.calculateDayCountFraction(startDate, endEnd));
    }

    @Test
    void test_bond_basis_act_360() {
        var convention = DayCountConvetionEnum.ACT360;
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endEnd = LocalDate.of(2023, 2, 28);
        assertEquals(59 / 360.0, convention.calculateDayCountFraction(startDate, endEnd));
    }

}