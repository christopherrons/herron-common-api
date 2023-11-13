package com.herron.exchange.common.api.common.parametricmodels.impliedvolsurface.model;

import com.herron.exchange.common.api.common.enums.SurfaceConstructionMethod;

import java.util.List;

public record ImpliedVolatilitySurfaceModelParameters(SurfaceConstructionMethod surfaceConstructionMethod,
                                                      List<ImpliedVolPoint> points) {
}
