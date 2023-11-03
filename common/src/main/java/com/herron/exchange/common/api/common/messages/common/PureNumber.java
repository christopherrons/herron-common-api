package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.enums.messagetypes.CommonMessageTypesEnum;

import java.math.BigDecimal;

import static com.herron.exchange.common.api.common.enums.messagetypes.CommonMessageTypesEnum.PURE_NUMBER;


public class PureNumber extends Amount<PureNumber> {
    public static final PureNumber ZERO = new PureNumber(0);

    private PureNumber(double pureNumber) {
        super(pureNumber);
    }

    private PureNumber(@JsonProperty("value") BigDecimal pureNumber) {
        super(pureNumber);
    }

    public static PureNumber create(double pureNumber) {
        return new PureNumber(pureNumber);
    }

    @Override
    protected PureNumber newInstance(double pureNumber) {
        return new PureNumber(pureNumber);
    }

    @Override
    protected PureNumber newInstance(BigDecimal pureNumber) {
        return new PureNumber(pureNumber);
    }

    @Override
    protected void validate(PureNumber amount) {
        // Always valid
    }

    @Override
    protected PureNumber emptyAmount() {
        return ZERO;
    }

    @Override
    public CommonMessageTypesEnum messageType() {
        return PURE_NUMBER;
    }

}
