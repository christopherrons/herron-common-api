package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Price extends Amount<Price> {

    private Price(double price) {
        super(price);
    }

    @Override
    protected Price newInstance(double price) {
        return Price.create(price);
    }

    @Override
    protected void validate(Price amount) {
        // Always valid
    }

    public static Price create(double price) {
        return new Price(price);
    }

}
