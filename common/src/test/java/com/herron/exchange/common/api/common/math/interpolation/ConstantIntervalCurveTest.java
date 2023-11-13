package com.herron.exchange.common.api.common.math.interpolation;

import com.herron.exchange.common.api.common.api.math.CartesianPoint2d;
import com.herron.exchange.common.api.common.math.interpolation.curves.ConstantIntervalCurve;
import com.herron.exchange.common.api.common.math.model.Point2d;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConstantIntervalCurveTest {

    @Test
    void test_constant_interpolation_2points() {
        List<CartesianPoint2d> points = List.of(
                new Point2d(1, 2),
                new Point2d(2, 4),
                new Point2d(3, 8)
        );
        var constantIntervalCurve = ConstantIntervalCurve.create(points);
        assertEquals(2, constantIntervalCurve.getFunctionValue(1.5));
        assertEquals(4, constantIntervalCurve.getFunctionValue(2.5));
    }
}