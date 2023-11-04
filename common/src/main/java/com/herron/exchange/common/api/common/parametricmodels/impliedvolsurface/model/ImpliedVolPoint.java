package com.herron.exchange.common.api.common.parametricmodels.impliedvolsurface.model;

import com.herron.exchange.common.api.common.api.math.CartesianPoint3d;

public record ImpliedVolPoint(double maturity, double logMoneyness, double impliedVolatility) implements CartesianPoint3d {
    @Override
    public double x() {
        return maturity;
    }

    @Override
    public double y() {
        return logMoneyness;
    }

    @Override
    public double z() {
        return impliedVolatility;
    }
}
