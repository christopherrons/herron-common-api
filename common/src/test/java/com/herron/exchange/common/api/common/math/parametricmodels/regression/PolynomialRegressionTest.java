package com.herron.exchange.common.api.common.math.parametricmodels.regression;

import org.junit.jupiter.api.Test;

class PolynomialRegressionTest {
    @Test
    void test_polynomial_regression_2_predictors_degree_2() {
        double[][] predictors = {
                {1, 2},
                {1, 3},
                {1, 4},

                {2, 2},
                {2, 3},
                {2, 4},

                {3, 2},
                {3, 3},
                {3, 4},

                {4, 2},
                {5, 3},
                {6, 4},

                {7, 2},
                {8, 3},
                {9, 4},
        };

        double[] labels = {4, 5, 6, 5, 6, 7, 6, 7, 8, 7, 8, 9, 8, 9, 10};

        var regression = PolynomialRegression.create(labels, predictors, 1);
        //   assertEquals(4, regression.getFunctionValue(1, 2), 0.01);
        //   assertEquals(5, regression.getFunctionValue(2, 2), 0.01);
    }
}