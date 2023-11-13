package com.herron.exchange.common.api.common.math.interpolation.surfaces;

import com.herron.exchange.common.api.common.api.math.CartesianPoint3d;
import com.herron.exchange.common.api.common.api.math.Function3d;
import com.herron.exchange.common.api.common.math.model.Interval;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.*;
import java.util.stream.Collectors;

import static com.herron.exchange.common.api.common.math.MathUtils.scaleValue;

public class HermiteBiCubicSurface implements Function3d {

    private final List<BiCupicPatch> patches;

    private HermiteBiCubicSurface(List<CartesianPoint3d> points) {
        this.patches = buildPatches(points);
    }

    public static HermiteBiCubicSurface create(List<CartesianPoint3d> points) {
        return new HermiteBiCubicSurface(points);
    }

    private List<BiCupicPatch> buildPatches(List<CartesianPoint3d> points) {
        CartesianPoint3d[][] grid = buildGrid(points);
        double[][] xDerivatives = buildXDerivativeGrid(grid);
        double[][] yDerivatives = buildYDerivativeGrid(grid);
        double[][] xyDerivatives = buildXYDerivativeGrid(grid);
        return buildPatches(grid, xDerivatives, yDerivatives, xyDerivatives);
    }

    private List<BiCupicPatch> buildPatches(CartesianPoint3d[][] grid,
                                            double[][] xDerivatives,
                                            double[][] yDerivatives,
                                            double[][] xyDerivatives) {
        List<BiCupicPatch> biCupicPatches = new ArrayList<>();
        for (int row = 0; row < grid.length - 1; row++) {
            for (int column = 0; column < grid[row].length - 1; column++) {
                Interval xInterval = new Interval(grid[row][column].x(), grid[row + 1][column].x());
                Interval yInterval = new Interval(grid[row][column].y(), grid[row][column + 1].y());

                double p00 = grid[row][column].z();
                double p10 = grid[row + 1][column].z();
                double p01 = grid[row][column + 1].z();
                double p11 = grid[row + 1][column + 1].z();

                double px00 = xDerivatives[row][column];
                double px10 = xDerivatives[row + 1][column];
                double px01 = xDerivatives[row][column + 1];
                double px11 = xDerivatives[row + 1][column + 1];

                double py00 = yDerivatives[row][column];
                double py10 = yDerivatives[row + 1][column];
                double py01 = yDerivatives[row][column + 1];
                double py11 = yDerivatives[row + 1][column + 1];

                double pxy00 = xyDerivatives[row][column];
                double pxy10 = xyDerivatives[row + 1][column];
                double pxy01 = xyDerivatives[row][column + 1];
                double pxy11 = xyDerivatives[row + 1][column + 1];

                double[][] coefficients = {
                        {p00, p01, py00, py01},
                        {p10, p11, py10, py11},
                        {px00, px01, pxy00, pxy01},
                        {px10, px11, pxy10, pxy11}
                };
                biCupicPatches.add(new BiCupicPatch(
                        xInterval,
                        yInterval,
                        coefficients
                ));
            }
        }

        return biCupicPatches;
    }

    private CartesianPoint3d[][] buildGrid(List<CartesianPoint3d> points) {
        Map<Double, List<CartesianPoint3d>> xValueToPoints = points.stream().collect(Collectors.groupingBy(CartesianPoint3d::x));
        Map<Double, List<CartesianPoint3d>> yValueToPoints = points.stream().collect(Collectors.groupingBy(CartesianPoint3d::y));
        xValueToPoints = new TreeMap<>(xValueToPoints);
        yValueToPoints = new TreeMap<>(yValueToPoints);
        List<Double> yValues = new ArrayList<>(yValueToPoints.keySet());
        yValues.sort(Double::compare);

        CartesianPoint3d[][] grid = new CartesianPoint3d[xValueToPoints.size()][yValueToPoints.size()];

        int row = 0;
        for (var entry : xValueToPoints.entrySet()) {
            List<CartesianPoint3d> pointsAtX = entry.getValue();
            pointsAtX.sort(Comparator.comparing(CartesianPoint3d::y));
            for (var point : pointsAtX) {
                int column = yValues.indexOf(point.y());
                grid[row][column] = point;
            }
        }

        return grid;
    }

    private double[][] buildXDerivativeGrid(CartesianPoint3d[][] grid) {
        return new double[][]{};
    }

    private double[][] buildYDerivativeGrid(CartesianPoint3d[][] grid) {
        return new double[][]{};
    }

    private double[][] buildXYDerivativeGrid(CartesianPoint3d[][] grid) {
        return new double[][]{};
    }

    @Override
    public double getFunctionValue(double x, double y) {
        var patch = patches.stream()
                .filter(p -> p.isInPatch(x, y))
                .findFirst()
                .orElseThrow();

        return patch.getFunctionValue(x, y);
    }

    private static class BiCupicPatch {
        private static final RealMatrix H = new Array2DRowRealMatrix(new double[][]{
                {2, -2, 1, 1},
                {-3, 3, -2, -1},
                {0, 0, 1, 0},
                {1, 0, 0, 0}
        });
        private static final RealMatrix HT = H.transpose();
        private final RealMatrix P;
        private final Interval xInterval;
        private final Interval yInterval;

        public BiCupicPatch(Interval xInterval,
                            Interval yInterval,
                            double[][] coefficients) {
            this.xInterval = xInterval;
            this.yInterval = yInterval;
            this.P = new Array2DRowRealMatrix(coefficients);
        }

        public double getFunctionValue(double x, double y) {
            double u = scaleValue(x, xInterval.startBoundaryPoint(), xInterval.startBoundaryPoint(), 0, 1);
            double v = scaleValue(y, yInterval.startBoundaryPoint(), yInterval.startBoundaryPoint(), 0, 1);
            RealVector U = createVectorU(u);
            RealVector V = createVectorV(v);
            return U.dotProduct(H.multiply(P.multiply(HT)).operate(V));
        }

        boolean isInPatch(double x, double y) {
            return xInterval.isInInterval(x) && yInterval.isInInterval(y);
        }

        private RealVector createVectorU(double u) {
            return new ArrayRealVector(
                    new double[]{
                            Math.pow(u, 3),
                            Math.pow(u, 2),
                            Math.pow(u, 1),
                            Math.pow(u, 0)
                    }
            );
        }

        private RealVector createVectorV(double v) {
            return new ArrayRealVector(
                    new double[]{
                            Math.pow(v, 3),
                            Math.pow(v, 2),
                            Math.pow(v, 1),
                            Math.pow(v, 0)
                    }
            );
        }
    }
}
