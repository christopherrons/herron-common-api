package com.herron.exchange.common.api.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MonetaryAmount(double value, String currency) {

    public boolean gt(MonetaryAmount otherMonetaryAmount) {
        return this.value > otherMonetaryAmount.value();
    }

    public boolean lt(MonetaryAmount otherMonetaryAmount) {
        return this.value < otherMonetaryAmount.value();
    }

    public boolean ge(MonetaryAmount otherMonetaryAmount) {
        return this.value >= otherMonetaryAmount.value();
    }

    public boolean le(MonetaryAmount otherMonetaryAmount) {
        return this.value <= otherMonetaryAmount.value();
    }

    public MonetaryAmount add(MonetaryAmount otherMonetaryAmount) {
        return new MonetaryAmount(value + otherMonetaryAmount.value(), currency);
    }

    public MonetaryAmount subtract(MonetaryAmount otherMonetaryAmount) {
        return new MonetaryAmount(value - otherMonetaryAmount.value(), currency);
    }

    public MonetaryAmount multiply(MonetaryAmount otherMonetaryAmount) {
        return new MonetaryAmount(value * otherMonetaryAmount.value(), currency);
    }

    public MonetaryAmount divide(MonetaryAmount otherMonetaryAmount) {
        return new MonetaryAmount(value * (1.0 / otherMonetaryAmount.value()), currency);
    }
}
