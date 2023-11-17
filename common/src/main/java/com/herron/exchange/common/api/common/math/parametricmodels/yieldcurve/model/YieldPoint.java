package com.herron.exchange.common.api.common.math.parametricmodels.yieldcurve.model;


import com.herron.exchange.common.api.common.api.math.CartesianPoint2d;

public record YieldPoint(double maturity, double yieldInDecimalForm) implements CartesianPoint2d {

    @Override
    public double x() {
        return maturity;
    }

    @Override
    public double y() {
        return yieldInDecimalForm;
    }
}
