package com.herron.exchange.common.api.common.parametricmodels.regression;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinearRegressionTest {

    @Test
    void test_linear_regression_2_predictors() {
        double[][] predictors = {
                {1, 2},
                {1, 3},
                {1, 4},

                {2, 2},
                {2, 3},
                {2, 4},

                {3, 2},
                {3, 3},
                {3, 4}
        };

        double[] labels = {4, 5, 6, 5, 6, 7, 6, 7, 8};

        var regression = LinearRegression.create(labels, predictors);
        assertEquals(4, regression.getFunctionValue(1, 2), 0.01);
        assertEquals(5, regression.getFunctionValue(2, 2), 0.01);
    }

}