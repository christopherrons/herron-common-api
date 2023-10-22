package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.enums.messagetypes.CommonMessageTypesEnum;

import java.math.BigDecimal;

import static com.herron.exchange.common.api.common.enums.messagetypes.CommonMessageTypesEnum.VOLUME;

public class Volume extends Amount<Volume> {
    public static final Volume ZERO = new Volume(0);

    private Volume(double volume) {
        super(volume);
    }

    private Volume(@JsonProperty("value") BigDecimal volume) {
        super(volume);
    }

    @Override
    protected Volume newInstance(BigDecimal volume) {
        return new Volume(volume);
    }

    @Override
    protected Volume newInstance(double volume) {
        return new Volume(volume);
    }

    @Override
    protected void validate(Volume value) {
        // Always valid
    }

    @Override
    protected Volume emptyAmount() {
        return ZERO;
    }

    public static Volume create(double volume) {
        return new Volume(volume);
    }

    @Override
    public CommonMessageTypesEnum messageType() {
        return VOLUME;
    }
}
