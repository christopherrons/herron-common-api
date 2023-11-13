package com.herron.exchange.common.api.common.math.model;

public record Interval(double startBoundaryPoint, double endBoundaryPoint) {

    public boolean isInInterval(double value) {
        return startBoundaryPoint() <= value && value <= endBoundaryPoint();
    }
}
