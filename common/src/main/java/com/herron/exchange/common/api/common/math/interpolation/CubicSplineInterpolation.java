package com.herron.exchange.common.api.common.math.interpolation;


import com.herron.exchange.common.api.common.api.math.CartesianPoint2d;
import com.herron.exchange.common.api.common.api.math.Function2d;
import com.herron.exchange.common.api.common.math.model.CubicPolynomial;
import com.herron.exchange.common.api.common.math.model.Interval2d;
import com.herron.exchange.common.api.common.math.solver.LinearEquationSolver;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CubicSplineInterpolation implements Function2d {

    private final List<CubicPolynomial> polynomials;

    private CubicSplineInterpolation(List<CartesianPoint2d> points) {
        this.polynomials = buildPolynomials(points);
    }

    public static CubicSplineInterpolation create(List<CartesianPoint2d> points) {
        return new CubicSplineInterpolation(points);
    }

    public double getFunctionValue(double x) {
        x = checkInputValue(x);
        for (CubicPolynomial polynomial : polynomials) {
            if (x < polynomial.getEndBoundaryPointX()) {
                return polynomial.getFunctionValue(x);
            }
        }
        return polynomials.get(polynomials.size() - 1).getFunctionValue(x);
    }

    public double getStartBoundaryX() {
        return polynomials.get(0).getStartBoundaryPointX();
    }

    public double getStartBoundaryY() {
        return polynomials.get(0).getStartBoundaryPointY();
    }

    public double getEndBoundaryX() {
        return polynomials.get(polynomials.size() - 1).getEndBoundaryPointX();
    }

    public double getEndBoundaryY() {
        return polynomials.get(polynomials.size() - 1).getEndBoundaryPointY();
    }

    private double checkInputValue(final double x) {
        if (x < getStartBoundaryX()) {
            return getStartBoundaryX();
        }
        return Math.min(x, getEndBoundaryX());
    }

    private List<CubicPolynomial> buildPolynomials(List<CartesianPoint2d> points) {
        points = new ArrayList<>(points);
        points.sort(Comparator.comparing(CartesianPoint2d::x));

        final int nrOfBoundaries = points.size() - 1;
        final List<Interval2d> boundaryPoints = new ArrayList<>();
        for (int i = 0; i < nrOfBoundaries; i++) {
            boundaryPoints.add(new Interval2d(
                            points.get(i),
                            points.get(i + 1)
                    )
            );
        }
        return createPolynomials(boundaryPoints);
    }

    private List<CubicPolynomial> createPolynomials(List<Interval2d> boundaryPoints) {
        RealVector coefficients = getCoefficients(boundaryPoints);
        final List<CubicPolynomial> polynomials = new ArrayList<>();
        for (int i = 0; i < boundaryPoints.size(); i++) {
            int rowIndex = i * 4;
            var polynomial = new CubicPolynomial(
                    coefficients.getEntry(rowIndex),
                    coefficients.getEntry(rowIndex + 1),
                    coefficients.getEntry(rowIndex + 2),
                    coefficients.getEntry(rowIndex + 3),
                    boundaryPoints.get(i)
            );
            polynomials.add(polynomial);
        }
        return polynomials;
    }

    private RealVector getCoefficients(List<Interval2d> boundaryPoints) {
        final CubicSplineLinearSystemBuilder linearSystemBuilder = new CubicSplineLinearSystemBuilder(boundaryPoints);
        final RealMatrix A = linearSystemBuilder.createCoefficientMatrix();
        final RealVector b = linearSystemBuilder.createConstantVector();
        return LinearEquationSolver.solveSystem(A, b);
    }

}
