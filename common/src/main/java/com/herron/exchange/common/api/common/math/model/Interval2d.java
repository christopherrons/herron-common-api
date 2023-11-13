package com.herron.exchange.common.api.common.math.model;

import com.herron.exchange.common.api.common.api.math.CartesianPoint2d;

public record Interval2d(CartesianPoint2d p1, CartesianPoint2d p2) {

    public double startBoundaryPointX() {
        return p1.x();
    }

    public double endBoundaryPointX() {
        return p2.x();
    }

    public double startBoundaryPointY() {
        return p1.y();
    }

    public double endBoundaryPointY() {
        return p2.y();
    }

    public boolean isInInterval(double x, double y) {
        return startBoundaryPointX() <= x && x <= endBoundaryPointX() &&
                startBoundaryPointY() <= y && y <= endBoundaryPointY();
    }
}
