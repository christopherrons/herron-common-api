package com.herron.exchange.common.api.common.math.interpolation.surfaces;

import com.herron.exchange.common.api.common.api.math.CartesianPoint3d;
import com.herron.exchange.common.api.common.math.model.Point3d;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HermiteBiCubicSurfaceTest {

    @Test
    void test_surface_construction_regular_grid() {
        List<CartesianPoint3d> points = List.of(
                new Point3d(1, 2, 4),
                new Point3d(1, 3, 5),
                new Point3d(1, 4, 6),

                new Point3d(2, 2, 5),
                new Point3d(2, 3, 6),
                new Point3d(2, 4, 7),

                new Point3d(3, 2, 6),
                new Point3d(3, 3, 7),
                new Point3d(3, 4, 8)
        );
        var surface = HermiteBiCubicSurface.create(points);

        assertEquals(4, surface.getFunctionValue(1, 2), 0.001);
        assertEquals(5, surface.getFunctionValue(1, 3), 0.001);
        assertEquals(6, surface.getFunctionValue(1, 4), 0.001);

        assertEquals(5, surface.getFunctionValue(2, 2), 0.001);
        assertEquals(6, surface.getFunctionValue(2, 3), 0.001);
        assertEquals(7, surface.getFunctionValue(2, 4), 0.001);

        assertEquals(6, surface.getFunctionValue(3, 2), 0.001);
        assertEquals(7, surface.getFunctionValue(3, 3), 0.001);
        assertEquals(8, surface.getFunctionValue(3, 4), 0.001);
    }
}