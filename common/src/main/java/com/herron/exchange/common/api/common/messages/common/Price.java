package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.enums.messagetypes.CommonMessageTypesEnum;

import java.math.BigDecimal;

import static com.herron.exchange.common.api.common.enums.messagetypes.CommonMessageTypesEnum.PRICE;

public class Price extends Amount<Price> {
    public static final Price ZERO = new Price(0);
    public static final Price EMPTY = null;

    private Price(double price) {
        super(price);
    }

    private Price(@JsonProperty("value") BigDecimal price) {
        super(price);
    }

    public static Price create(double price) {
        return new Price(price);
    }

    @Override
    protected Price newInstance(double price) {
        return new Price(price);
    }

    @Override
    protected Price newInstance(BigDecimal price) {
        return new Price(price);
    }

    @Override
    protected void validate(Price amount) {
        // Always valid
    }

    @Override
    protected Price emptyAmount() {
        return ZERO;
    }

    @Override
    public CommonMessageTypesEnum messageType() {
        return PRICE;
    }

}
