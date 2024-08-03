package com.herron.exchange.common.api.common.math.parametricmodels.regression;

import com.herron.exchange.common.api.common.api.math.FunctionNd;
import org.apache.commons.math3.linear.*;

import java.util.Objects;

public class LinearRegression implements FunctionNd {

    private final RealVector coefficients;
    private final int nrOfPredictors;

    public LinearRegression(double[] labels, double[][] samples) {
        this.nrOfPredictors = samples[0].length;
        this.coefficients = buildCoefficients(labels, samples);
    }

    public static LinearRegression create(double[] labels, double[][] samples) {
        return new LinearRegression(labels, samples);
    }

    private RealVector buildCoefficients(double[] labels, double[][] samples) {
        RealVector y = new ArrayRealVector(labels);

        int nrOfRows = samples.length;
        int nrOfColumns = samples[0].length + 1;
        double[][] predictorsWithBias = new double[nrOfRows][nrOfColumns];
        for (int i = 0; i < nrOfRows; i++) {
            predictorsWithBias[i][0] = 1.0;
            System.arraycopy(samples[i], 0, predictorsWithBias[i], 1, nrOfColumns - 1);
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
        RealVector x = new ArrayRealVector(predictors);
        return x.dotProduct(coefficients.getSubVector(1, coefficients.getDimension() - 1)) + coefficients.getEntry(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinearRegression that)) return false;
        return nrOfPredictors == that.nrOfPredictors && Objects.equals(coefficients, that.coefficients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coefficients, nrOfPredictors);
    }
}
