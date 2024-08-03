package com.herron.exchange.common.api.common.math.parametricmodels.regression;

import com.herron.exchange.common.api.common.api.math.FunctionNd;
import org.apache.commons.math3.linear.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PolynomialRegression implements FunctionNd {

    private final RealVector coefficients;
    private final int nrOfPredictors;
    private final int polynomialDegree;

    public PolynomialRegression(double[] labels, double[][] samples, int degree) {
        this.nrOfPredictors = samples[0].length;
        this.polynomialDegree = degree;
        this.coefficients = buildCoefficients(labels, samples);
    }

    public static PolynomialRegression create(double[] labels, double[][] samples, int degree) {
        return new PolynomialRegression(labels, samples, degree);
    }

    private RealVector buildCoefficients(double[] labels, double[][] samples) {
        RealVector y = new ArrayRealVector(labels);
        int nrOfRows = samples.length;
        int nrOfColumns = samples[0].length * polynomialDegree + 1;

        double[][] predictorsWithBias = new double[nrOfRows][nrOfColumns];

        for (int row = 0; row < nrOfRows; row++) {
            predictorsWithBias[row] = createPolynomial(samples[row]);
        }

        RealMatrix X = new Array2DRowRealMatrix(predictorsWithBias);
        RealMatrix XT = X.transpose();
        LUDecomposition decomposition = new LUDecomposition(XT.multiply(X));
        return decomposition.getSolver().getInverse().multiply(XT).operate(y);
    }

    public double getFunctionValue(double... predictors) {
        if (predictors.length < nrOfPredictors) {
            throw new IllegalArgumentException(String.format("Predictors %s does not match required %s", predictors.length, nrOfPredictors));
        }
        RealVector x = new ArrayRealVector(createPolynomial(predictors));
        return x.dotProduct(coefficients);
    }

    private double[] createPolynomial(double[] predictors) {
        List<Double> polynomials = new ArrayList<>();
        polynomials.add(1.0);
        for (int i = 0; i < predictors.length; i++) {
            double currentPredictors = predictors[i];

            for (int degree = 1; degree <= polynomialDegree; degree++) {
                polynomials.add(Math.pow(currentPredictors, degree));
            }

            for (int j = 0; j < predictors.length; j++) {
                if (i == j) {
                    continue;
                }

                double otherPredictors = predictors[j];
                //    polynomials.add(currentPredictors * otherPredictors);
             /*   for (int degree = 1; degree < polynomialDegree; degree++) {
                    polynomials.add(Math.pow(currentPredictors, degree) * otherPredictors);
                }*/
            }
        }
        return polynomials.stream()
                .mapToDouble(Double::doubleValue)
                .toArray();
    }

    public int getPolynomialDegree() {
        return polynomialDegree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PolynomialRegression that)) return false;
        return nrOfPredictors == that.nrOfPredictors && polynomialDegree == that.polynomialDegree && Objects.equals(coefficients, that.coefficients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coefficients, nrOfPredictors, polynomialDegree);
    }
}
