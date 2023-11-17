package com.herron.exchange.common.api.common.math.parametricmodels.forwardcurve.model;

import com.herron.exchange.common.api.common.enums.InterpolationMethod;

import java.util.List;

public record ForwardCurveModelParameters(List<ForwardPricePoint> forwardPricePoints, InterpolationMethod interpolationMethod) {
}
