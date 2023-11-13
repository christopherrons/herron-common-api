package com.herron.exchange.common.api.common.math.interpolation;

import com.herron.exchange.common.api.common.api.math.CartesianPoint2d;
import com.herron.exchange.common.api.common.math.interpolation.curves.LinearInterpolationCurve;
import com.herron.exchange.common.api.common.math.model.Point2d;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinearInterpolationCurveTest {

    @Test
    void test_linear_interpolation_2points() {
        List<CartesianPoint2d> points = List.of(
                new Point2d(1, 2),
                new Point2d(2, 4),
                new Point2d(4, 8)
        );
        var linearInterpolation = LinearInterpolationCurve.create(points);
        assertEquals(3, linearInterpolation.getFunctionValue(1.5));
        assertEquals(6, linearInterpolation.getFunctionValue(3));
    }

}