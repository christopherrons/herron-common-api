package com.herron.exchange.common.api.common.math.interpolation.surfaces;

import com.herron.exchange.common.api.common.api.math.CartesianPoint3d;
import com.herron.exchange.common.api.common.math.model.Point3d;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

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

        double[] randomX = generateRandomDoubles(100, 1.0, 3.0);
        double[] randomY = generateRandomDoubles(100, 2.0, 4.0);
        double[] z = new double[randomY.length * randomX.length];
        int i = 0;
        for (var x : randomX) {
            for (var y : randomY) {
                z[i] = surface.getFunctionValue(x, y);
                i++;
            }
        }
        writeRandomDoublesToCSV("/home/christopher/test.csv", randomX, randomY, z);
    }

    private static double[] generateRandomDoubles(int count, double min, double max) {
        double[] randomDoubles = new double[count];
        Random random = new Random();
        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        for (int i = 0; i < count; i++) {

            randomDoubles[i] = Double.parseDouble(decimalFormat.format(min + (max - min) * random.nextDouble()));
        }

        return randomDoubles;
    }

    private static void writeRandomDoublesToCSV(String filePath, double[] randomX, double[] randomY, double[] z) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("maturity,logMoneyness,impliedVolatility");
            writer.write(System.lineSeparator());
            for (int i = 0; i < randomX.length; i++) {
                writer.write(String.format("%s,%s,%.3f", randomX[i], randomY[i], z[i]));
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}