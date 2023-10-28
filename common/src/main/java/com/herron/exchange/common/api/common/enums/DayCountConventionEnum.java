package com.herron.exchange.common.api.common.enums;

import com.herron.exchange.common.api.common.messages.common.Timestamp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public enum DayCountConventionEnum {
    ACT365(0, 365),
    ACT360(0, 360),
    BOND_BASIS_30360(30, 360);

    private final double daysPerMonth;
    private final double daysPerYear;

    DayCountConventionEnum(double daysPerMonth, double daysPerYear) {
        this.daysPerMonth = daysPerMonth;
        this.daysPerYear = daysPerYear;
    }

    public double calculateDayCountFraction(Timestamp startDate, Timestamp endDate) {
        return calculateDayCountFraction(startDate.toLocalDate(), endDate.toLocalDate());
    }

    public double calculateDayCountFraction(LocalDate startDate, LocalDate endDate) {
        return switch (this) {
            case BOND_BASIS_30360 -> {
                LocalDate adjustedStart = startDate.getDayOfMonth() == 31 ? startDate.minusDays(1) : startDate;
                LocalDate adjustedEndDate = endDate.plusDays(1);
                adjustedEndDate = (adjustedEndDate.getDayOfMonth() == 31 && startDate.getDayOfMonth() > 29) ?
                        adjustedEndDate.minusDays(1) : adjustedEndDate;
                int yearsBetween = adjustedEndDate.getYear() - adjustedStart.getYear();
                int monthsBetween = adjustedEndDate.getMonthValue() - adjustedStart.getMonthValue();
                int daysBetween = adjustedEndDate.getDayOfMonth() - adjustedStart.getDayOfMonth();
                yield (daysPerYear * yearsBetween + daysPerMonth * monthsBetween + daysBetween) / daysPerYear;
            }
            default -> ChronoUnit.DAYS.between(startDate, endDate.plusDays(1)) / daysPerYear;
        };
    }

    public double getDaysPerMonth() {
        return daysPerMonth;
    }

    public double getDaysPerYear() {
        return daysPerYear;
    }
}

