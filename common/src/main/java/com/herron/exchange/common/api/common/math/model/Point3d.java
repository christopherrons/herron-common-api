package com.herron.exchange.common.api.common.math.model;

import com.herron.exchange.common.api.common.api.math.CartesianPoint3d;

public record Point3d(double x, double y, double z) implements CartesianPoint3d {
}
