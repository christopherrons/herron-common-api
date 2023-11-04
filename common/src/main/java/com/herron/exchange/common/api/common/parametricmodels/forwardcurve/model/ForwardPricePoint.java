package com.herron.exchange.common.api.common.parametricmodels.forwardcurve.model;


import com.herron.exchange.common.api.common.api.math.CartesianPoint2d;

public record ForwardPricePoint(double maturity, double forwardPrice) implements CartesianPoint2d {

    @Override
    public double x() {
        return maturity;
    }

    @Override
    public double y() {
        return forwardPrice;
    }
}
