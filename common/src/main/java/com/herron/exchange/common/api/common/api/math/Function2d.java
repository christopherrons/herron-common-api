package com.herron.exchange.common.api.common.api.math;

public interface Function2d {

    double getFunctionValue(double x);

    double getStartBoundaryX();

    double getStartBoundaryY();

    double getEndBoundaryX();

    double getEndBoundaryY();
}
