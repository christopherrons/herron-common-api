package com.herron.exchange.common.api.common.math.probability;

import com.herron.exchange.common.api.common.testutils.TestAssertionUtils;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.herron.exchange.common.api.common.math.probability.ProbabilityCalculationUtils.*;

class ProbabilityCalculationUtilsTest {

    @Test
    void testMean() {
        List<Double> values = new ArrayList<>();
        values.add(2.0);
        values.add(1.0);
        values.add(6.0);
        double mean = calculateMean(values);
        TestAssertionUtils.assertDouble(3.0, mean, 0.0000001);
    }

    @Test
    void testVariance() {
        List<Double> values = new ArrayList<>();
        values.add(2.0);
        values.add(1.0);
        values.add(6.0);
        double variance = calculateVariance(values);
        TestAssertionUtils.assertDouble(7, variance, 0.0000001);
    }

    @Test
    void testStandardDeviation() {
        List<Double> values = new ArrayList<>();
        values.add(2.0);
        values.add(1.0);
        values.add(6.0);
        double variance = calculateStandardDeviation(values);
        TestAssertionUtils.assertDouble(Math.sqrt(7), variance, 0.0000001);
    }

    @Test
    void testCovariance() {
        double[] firstValues = {1.0, 3.0, -1.0};
        double[] secondValues = {1.0, 0.0, -1.0};

        double variance = calculateCovariance(new ArrayRealVector(firstValues), new ArrayRealVector(secondValues), 1, 0);
        TestAssertionUtils.assertDouble(1.0, variance, 0.0000001);
    }

}