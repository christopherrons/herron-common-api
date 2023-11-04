package com.herron.exchange.common.api.common.parametricmodels.impliedvolsurface;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImpliedVolatilitySurface {

    private final String id;
    private final double value;

    public ImpliedVolatilitySurface(@JsonProperty("id") String id, @JsonProperty("value") double value) {
        this.id = id;
        this.value = value;
    }

    public static ImpliedVolatilitySurface create(String id, double value) {
        return new ImpliedVolatilitySurface(id, value);
    }

    public double getImpliedVolatility(double timeToMaturity, double logMoneyness) {
        return value;
    }

    public String getId() {
        return id;
    }

    public double getValue() {
        return value;
    }
}
