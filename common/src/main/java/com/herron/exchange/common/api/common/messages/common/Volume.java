package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

public class Volume extends Amount<Volume> {

    private Volume(@JsonProperty("value") double volume) {
        super(volume);
    }

    @Override
    protected Volume newInstance(double volume) {
        return Volume.create(volume);
    }

    @Override
    protected void validate(Volume value) {
        // Always valid
    }

    public static Volume create(double volume) {
        return new Volume(volume);
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.VOLUME;
    }
}
