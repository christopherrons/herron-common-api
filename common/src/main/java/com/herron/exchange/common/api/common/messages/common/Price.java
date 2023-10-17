package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

public class Price extends Amount<Price> {

    public Price(@JsonProperty("value") double price) {
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

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.PRICE;
    }
}