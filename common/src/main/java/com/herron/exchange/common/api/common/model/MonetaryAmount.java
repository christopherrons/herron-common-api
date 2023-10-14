package com.herron.exchange.common.api.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MonetaryAmount(double value, String currency) {

    public static MonetaryAmount ZERO_AMOUNT = new MonetaryAmount(0, "N/A");

    public boolean gt(MonetaryAmount otherMonetaryAmount) {
        validate(otherMonetaryAmount);
        return this.value > otherMonetaryAmount.value();
    }

    public boolean lt(MonetaryAmount otherMonetaryAmount) {
        validate(otherMonetaryAmount);
        return this.value < otherMonetaryAmount.value();
    }

    public boolean ge(MonetaryAmount otherMonetaryAmount) {
        validate(otherMonetaryAmount);
        return this.value >= otherMonetaryAmount.value();
    }

    public boolean le(MonetaryAmount otherMonetaryAmount) {
        validate(otherMonetaryAmount);
        return this.value <= otherMonetaryAmount.value();
    }

    public MonetaryAmount add(MonetaryAmount otherMonetaryAmount) {
        validate(otherMonetaryAmount);
        return new MonetaryAmount(value + otherMonetaryAmount.value(), currency);
    }

    public MonetaryAmount subtract(MonetaryAmount otherMonetaryAmount) {
        validate(otherMonetaryAmount);
        return new MonetaryAmount(value - otherMonetaryAmount.value(), currency);
    }

    public MonetaryAmount multiply(MonetaryAmount otherMonetaryAmount) {
        validate(otherMonetaryAmount);
        return new MonetaryAmount(value * otherMonetaryAmount.value(), currency);
    }

    public MonetaryAmount divide(MonetaryAmount otherMonetaryAmount) {
        validate(otherMonetaryAmount);
        return new MonetaryAmount(value * (1.0 / otherMonetaryAmount.value()), currency);
    }

    private void validate(MonetaryAmount otherMonetaryAmount) {
        if (currency.equals(otherMonetaryAmount.currency)) {
            throw new IllegalArgumentException(String.format("Currency %s and %s are incompatible.", currency, otherMonetaryAmount.currency));
        }
    }
}
