package com.herron.exchange.common.api.common.math.parametricmodels.forwardcurve;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.herron.exchange.common.api.common.api.math.CartesianPoint2d;
import com.herron.exchange.common.api.common.api.math.Function2d;
import com.herron.exchange.common.api.common.math.interpolation.curves.ConstantIntervalCurve;
import com.herron.exchange.common.api.common.math.interpolation.curves.CubicSplineInterpolation;
import com.herron.exchange.common.api.common.math.interpolation.curves.LinearInterpolationCurve;
import com.herron.exchange.common.api.common.math.parametricmodels.forwardcurve.model.ForwardCurveModelParameters;

import java.util.Objects;

public class ForwardPriceCurve {

    private final String instrumentId;
    private final ForwardCurveModelParameters forwardCurveModelParameters;
    private final Function2d forwardPriceFunction;

    private ForwardPriceCurve(@JsonProperty("id") String instrumentId,
                              @JsonProperty("forwardCurveModelParameters") ForwardCurveModelParameters forwardCurveModelParameters) {
        this.instrumentId = instrumentId;
        this.forwardCurveModelParameters = forwardCurveModelParameters;
        this.forwardPriceFunction = createForwardPriceFunction();
    }

    public static ForwardPriceCurve create(String id, ForwardCurveModelParameters forwardCurveModelParameters) {
        return new ForwardPriceCurve(id, forwardCurveModelParameters);
    }

    private Function2d createForwardPriceFunction() {
        var points = forwardCurveModelParameters.forwardPricePoints().stream().map(CartesianPoint2d.class::cast).toList();
        return switch (forwardCurveModelParameters.interpolationMethod()) {
            case CUBIC_SPLINE -> CubicSplineInterpolation.create(points);
            case CONSTANT -> ConstantIntervalCurve.create(points);
            case LINEAR -> LinearInterpolationCurve.create(points);
            default -> throw new IllegalArgumentException("Other methods are not supported");
        };
    }

    public double getForwardPrice(double maturity) {
        return forwardPriceFunction.getFunctionValue(maturity);
    }

    public double getStartBoundaryMaturity() {
        return forwardPriceFunction.getStartBoundaryX();
    }

    public double getStartBoundaryYield() {
        return forwardPriceFunction.getStartBoundaryY();
    }

    public double getEndBoundaryMaturity() {
        return forwardPriceFunction.getEndBoundaryX();
    }

    public double getEndBoundaryYield() {
        return forwardPriceFunction.getEndBoundaryY();
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public ForwardCurveModelParameters getForwardCurveModelParameters() {
        return forwardCurveModelParameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ForwardPriceCurve that)) return false;
        return Objects.equals(instrumentId, that.instrumentId) && Objects.equals(forwardCurveModelParameters, that.forwardCurveModelParameters) && Objects.equals(forwardPriceFunction, that.forwardPriceFunction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instrumentId, forwardCurveModelParameters, forwardPriceFunction);
    }
}
