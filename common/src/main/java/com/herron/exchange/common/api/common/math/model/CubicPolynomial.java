package com.herron.exchange.common.api.common.math.model;

import static java.lang.Math.pow;

public record CubicPolynomial(double firstCoefficient,
                              double secondCoefficient,
                              double thirdCoefficient,
                              double fourthCoefficient,
                              Interval2d boundaryPoints) {

    public CubicPolynomial(double firstCoefficient,
                           double secondCoefficient,
                           double thirdCoefficient,
                           double fourthCoefficient) {
        this(firstCoefficient,
                secondCoefficient,
                thirdCoefficient,
                fourthCoefficient,
                new Interval2d(new Point2d(Double.MIN_VALUE, Double.MAX_VALUE), new Point2d(Double.MIN_VALUE, Double.MAX_VALUE))
        );
    }

    public double getFunctionValue(final double x) {
        return firstCoefficient * pow(x, 3) +
                secondCoefficient * pow(x, 2) +
                thirdCoefficient * x +
                fourthCoefficient;
    }

    public double getFirstDerivative(final double x) {
        return 3 * firstCoefficient * pow(x, 2) +
                2 * secondCoefficient * x +
                thirdCoefficient;
    }

    public double getSecondDerivative(final double x) {
        return 6 * firstCoefficient * x +
                2 * secondCoefficient;
    }

    public double getStartBoundaryPointX() {
        return boundaryPoints.startBoundaryPointX();
    }

    public double getEndBoundaryPointX() {
        return boundaryPoints.endBoundaryPointX();
    }

    public double getStartBoundaryPointY() {
        return boundaryPoints.startBoundaryPointY();
    }

    public double getEndBoundaryPointY() {
        return boundaryPoints.endBoundaryPointY();
    }

}
