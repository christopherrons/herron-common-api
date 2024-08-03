package com.herron.exchange.common.api.common.math.parametricmodels.yieldcurve;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.api.math.CartesianPoint2d;
import com.herron.exchange.common.api.common.api.math.Function2d;
import com.herron.exchange.common.api.common.math.interpolation.curves.ConstantIntervalCurve;
import com.herron.exchange.common.api.common.math.interpolation.curves.CubicSplineInterpolation;
import com.herron.exchange.common.api.common.math.interpolation.curves.LinearInterpolationCurve;
import com.herron.exchange.common.api.common.math.parametricmodels.yieldcurve.model.YieldCurveModelParameters;

import java.util.Objects;

public class YieldCurve {

    private final YieldCurveModelParameters yieldCurveModelParameters;
    private final String id;
    private final Function2d yieldFunction;

    private YieldCurve(@JsonProperty("id") String id, @JsonProperty("yieldCurveModelParameters") YieldCurveModelParameters yieldCurveModelParameters) {
        this.id = id;
        this.yieldCurveModelParameters = yieldCurveModelParameters;
        this.yieldFunction = createYieldFunction();
    }

    public static YieldCurve create(String id, YieldCurveModelParameters yieldCurveModelParameters) {
        return new YieldCurve(id, yieldCurveModelParameters);
    }

    private Function2d createYieldFunction() {
        var points = yieldCurveModelParameters.yieldPoints().stream().map(CartesianPoint2d.class::cast).toList();
        return switch (yieldCurveModelParameters.interpolationMethod()) {
            case CUBIC_SPLINE -> CubicSplineInterpolation.create(points);
            case CONSTANT -> ConstantIntervalCurve.create(points);
            case LINEAR -> LinearInterpolationCurve.create(points);
        };
    }

    public double getYield(double maturity) {
        return yieldFunction.getFunctionValue(maturity);
    }

    public double getStartBoundaryMaturity() {
        return yieldFunction.getStartBoundaryX();
    }

    public double getStartBoundaryYield() {
        return yieldFunction.getStartBoundaryY();
    }

    public double getEndBoundaryMaturity() {
        return yieldFunction.getEndBoundaryX();
    }

    public double getEndBoundaryYield() {
        return yieldFunction.getEndBoundaryY();
    }

    public String getId() {
        return id;
    }

    public YieldCurveModelParameters getYieldCurveModelParameters() {
        return yieldCurveModelParameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof YieldCurve that)) return false;
        return Objects.equals(yieldCurveModelParameters, that.yieldCurveModelParameters) && Objects.equals(id, that.id) && Objects.equals(yieldFunction, that.yieldFunction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(yieldCurveModelParameters, id, yieldFunction);
    }
}
