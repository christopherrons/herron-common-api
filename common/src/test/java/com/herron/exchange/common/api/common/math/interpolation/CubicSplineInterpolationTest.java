package com.herron.exchange.common.api.common.math.interpolation;

import com.herron.exchange.common.api.common.testutils.TestAssertionUtils;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

class CubicSplineInterpolationTest {

    @Test
    void testCubicSplineInterpolation3Points() {
        CubicSplineInterpolation interpolation = CubicSplineInterpolation.create(CubicSplintInterpolationTestUtils.get3Points());
        for (int i = 0; i < 10000; i++) {
            double x = ThreadLocalRandom.current().nextDouble(1, 3);
            TestAssertionUtils.assertDouble(CubicSplintInterpolationTestUtils.getFunctionValue3Points(x), interpolation.getFunctionValue(x), 0.0000001);
        }
    }


    @Test
    void testCubicSplineInterpolation5Points() {
        CubicSplineInterpolation interpolation = CubicSplineInterpolation.create(CubicSplintInterpolationTestUtils.get5Points());
        for (int i = 0; i < 10000; i++) {
            double x = ThreadLocalRandom.current().nextDouble(1, 3);
            TestAssertionUtils.assertDouble(CubicSplintInterpolationTestUtils.getFunctionValue5Points(x), interpolation.getFunctionValue(x), 0.0000001);
        }
    }
}