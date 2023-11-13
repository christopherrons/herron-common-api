package com.herron.exchange.common.api.common.math.interpolation.surfaces;

import com.herron.exchange.common.api.common.api.math.CartesianPoint3d;
import com.herron.exchange.common.api.common.math.model.Point3d;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HermiteBiCubicSurfaceTest {

    @Test
    void test_surface_construction_uniform_grid() {
        List<CartesianPoint3d> points = List.of(
                new Point3d(0, 0, 5),
                new Point3d(1, 0, 6),
                new Point3d(2, 0, 3),
                new Point3d(3, 0, 5),

                new Point3d(0, 1, 3),
                new Point3d(1, 1, 5),
                new Point3d(2, 1, 6),
                new Point3d(3, 1, 3),

                new Point3d(0, 2, 4),
                new Point3d(1, 2, 3),
                new Point3d(2, 2, 5),
                new Point3d(3, 2, 6),

                new Point3d(0, 3, 5),
                new Point3d(1, 3, 4),
                new Point3d(2, 3, 3),
                new Point3d(3, 3, 5)
        );
        var surface = HermiteBiCubicSurface.create(points);

        assertEquals(3, surface.getFunctionValue(0, 2));
       // assertEquals(2, surface.getFunctionValue(1, 1));
       // assertEquals(3, surface.getFunctionValue(2, 2));
        //assertEquals(4, surface.getFunctionValue(3, 3));
    }

}