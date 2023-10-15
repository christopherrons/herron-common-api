package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Volume extends Amount<Volume> {

    private Volume(double volume) {
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

}
