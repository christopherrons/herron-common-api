package com.herron.exchange.common.api.common.math.interpolation;


import com.herron.exchange.common.api.common.api.math.CartesianPoint2d;
import com.herron.exchange.common.api.common.api.math.Function2d;
import com.herron.exchange.common.api.common.math.model.Interval2d;

import java.util.List;
import java.util.stream.IntStream;

public class LinearInterpolationCurve implements Function2d {

    private final List<Interval2d> intervals;

    private LinearInterpolationCurve(List<CartesianPoint2d> points) {
        this.intervals = IntStream.range(0, points.size() - 1)
                .mapToObj(i -> new Interval2d(points.get(i), points.get(i + 1)))
                .toList();
    }

    public static LinearInterpolationCurve create(List<CartesianPoint2d> points) {
        return new LinearInterpolationCurve(points);
    }

    public double getFunctionValue(double x) {
        x = checkInputValue(x);
        for (Interval2d interval : intervals) {
            if (x < interval.endBoundaryPointX()) {
                var y1 = interval.p1().y();
                var y2 = interval.p2().y();
                var x1 = interval.p1().x();
                var x2 = interval.p2().x();
                var k = (y2 - y1) / (x2 - x1);
                return y1 + k * (x - x1);
            }
        }
        return Double.NaN;
    }

    public double getStartBoundaryX() {
        return intervals.get(0).startBoundaryPointX();
    }

    public double getStartBoundaryY() {
        return intervals.get(0).startBoundaryPointY();
    }

    public double getEndBoundaryX() {
        return intervals.get(intervals.size() - 1).endBoundaryPointX();
    }

    public double getEndBoundaryY() {
        return intervals.get(intervals.size() - 1).endBoundaryPointY();
    }

    private double checkInputValue(final double x) {
        if (x < getStartBoundaryX()) {
            return getStartBoundaryX();
        }
        return Math.min(x, getEndBoundaryX());
    }
}
