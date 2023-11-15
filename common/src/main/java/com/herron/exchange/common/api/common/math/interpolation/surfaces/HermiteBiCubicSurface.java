package com.herron.exchange.common.api.common.math.interpolation.surfaces;

import com.herron.exchange.common.api.common.api.math.CartesianPoint2d;
import com.herron.exchange.common.api.common.api.math.CartesianPoint3d;
import com.herron.exchange.common.api.common.api.math.Function3d;
import com.herron.exchange.common.api.common.math.interpolation.curves.CubicSplineInterpolation;
import com.herron.exchange.common.api.common.math.model.Interval;
import com.herron.exchange.common.api.common.math.model.Point2d;
import com.herron.exchange.common.api.common.math.model.Point3d;
import com.herron.exchange.common.api.common.parametricmodels.regression.PolynomialRegression;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.herron.exchange.common.api.common.math.MathUtils.scaleValue;

public class HermiteBiCubicSurface implements Function3d {

    private final List<BiCubicPatch> patches;

    private HermiteBiCubicSurface(List<CartesianPoint3d> points) {
        this.patches = buildPatches(points);
    }

    public static HermiteBiCubicSurface create(List<CartesianPoint3d> points) {
        return new HermiteBiCubicSurface(points);
    }

    private List<BiCubicPatch> buildPatches(List<CartesianPoint3d> points) {
        CartesianPoint3d[][] grid = buildGrid(points);
        double[][] xDerivatives = buildXDerivativeGrid(grid);
        double[][] yDerivatives = buildYDerivativeGrid(grid);
        double[][] xyDerivatives = buildXYDerivativeGrid(grid);
        return buildPatches(grid, xDerivatives, yDerivatives, xyDerivatives);
    }

    private List<BiCubicPatch> buildPatches(CartesianPoint3d[][] grid,
                                            double[][] xDerivatives,
                                            double[][] yDerivatives,
                                            double[][] xyDerivatives) {
        List<BiCubicPatch> biCubicPatches = new ArrayList<>();
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
                biCubicPatches.add(new BiCubicPatch(
                        xInterval,
                        yInterval,
                        coefficients
                ));
            }
        }

        return biCubicPatches;
    }

    private CartesianPoint3d[][] buildGrid(List<CartesianPoint3d> points) {
        Map<Double, List<CartesianPoint3d>> xValueToPoints = new TreeMap<>();
        Map<Double, List<CartesianPoint3d>> yValueToPoints = new TreeMap<>();
        double[] labels = new double[points.size()];
        double[][] predictors = new double[points.size()][2];
        int item = 0;
        for (var point : points) {
            xValueToPoints.computeIfAbsent(point.x(), k -> new ArrayList<>()).add(point);
            yValueToPoints.computeIfAbsent(point.y(), k -> new ArrayList<>()).add(point);
            labels[item] = point.z();
            predictors[item][0] = point.x();
            predictors[item][1] = point.y();
            item++;
        }

        PolynomialRegression regressionSurface = PolynomialRegression.create(labels, predictors, 3);

        CartesianPoint3d[][] grid = new CartesianPoint3d[xValueToPoints.size()][yValueToPoints.size()];
        int row = 0;
        for (var entry : xValueToPoints.entrySet()) {
            double x = entry.getKey();
            Map<Double, List<CartesianPoint3d>> pointsAtX = entry.getValue().stream().collect(Collectors.groupingBy(CartesianPoint3d::y));
            int column = 0;
            for (double y : yValueToPoints.keySet()) {
                if (pointsAtX.containsKey(y)) {
                    grid[row][column] = pointsAtX.get(y).get(0);
                } else {
                    grid[row][column] = new Point3d(x, y, regressionSurface.getFunctionValue(x, y));
                }
                column++;
            }
            row++;
        }

        return grid;
    }

    private double[][] buildXDerivativeGrid(CartesianPoint3d[][] grid) {
        int nrOfRows = grid.length;
        int nrOfColumns = grid[0].length;
        double[][] xDerivativeGrid = new double[nrOfRows][nrOfColumns];

        for (int column = 0; column < nrOfColumns; column++) {
            List<CartesianPoint2d> points = new ArrayList<>();
            for (int row = 0; row < nrOfRows; row++) {
                var point = grid[row][column];
                points.add(new Point2d(point.x(), point.z()));
            }

            var spline = CubicSplineInterpolation.create(points);
            for (int row = 0; row < nrOfRows; row++) {
                var point = grid[row][column];
                xDerivativeGrid[row][column] = spline.getFirstDerivative(point.x());
            }
        }

        return xDerivativeGrid;
    }

    private double[][] buildYDerivativeGrid(CartesianPoint3d[][] grid) {
        int nrOfRows = grid.length;
        int nrOfColumns = grid[0].length;
        double[][] yDerivativeGrid = new double[nrOfRows][nrOfColumns];
        for (int row = 0; row < nrOfRows; row++) {
            List<CartesianPoint2d> points = new ArrayList<>();
            for (int column = 0; column < nrOfColumns; column++) {
                var point = grid[row][column];
                points.add(new Point2d(point.y(), point.z()));
            }

            var spline = CubicSplineInterpolation.create(points);
            for (int column = 0; column < nrOfColumns; column++) {
                var point = grid[row][column];
                yDerivativeGrid[row][column] = spline.getFirstDerivative(point.y());
            }
        }

        return yDerivativeGrid;
    }

    private double[][] buildXYDerivativeGrid(CartesianPoint3d[][] grid) {
        int nrOfRows = grid.length;
        int nrOfColumns = grid[0].length;
        double[][] xyDerivativeGrid = new double[nrOfRows][nrOfColumns];
        for (int row = 0; row < nrOfRows; row++) {
            for (int column = 0; column < nrOfColumns; column++) {
                double xyFiniteDifference;
                double x_current = grid[row][column].x();
                double y_current = grid[row][column].y();
                double z_current_xy = grid[row][column].z();

                if ((row == 0 && column == 0) || (row == 0 && column == nrOfColumns - 1) || (row == nrOfRows - 1 && column == 0) || (row == nrOfRows - 1 && column == nrOfColumns - 1)) {
                    xyFiniteDifference = 0.0;

                } else if ((row == 0 && column != nrOfColumns - 1) || (column == 0 && row != nrOfRows - 1)) {
                    double x_next = grid[row + 1][column].x();
                    double x_diff = x_next - x_current;

                    double y_next = grid[row][column + 1].y();
                    double y_diff = y_next - y_current;

                    double z_next_xy = grid[row + 1][column + 1].z();
                    xyFiniteDifference = (z_next_xy - z_current_xy) / (x_diff * y_diff);

                } else if ((row == nrOfRows - 1 && column != 0) || (column == nrOfColumns - 1 && row != 0)) {
                    double x_previous = grid[row - 1][column].x();
                    double x_diff = x_current - x_previous;

                    double y_previous = grid[row][column - 1].y();
                    double y_diff = y_current - y_previous;

                    double z_previous_xy = grid[row - 1][column - 1].z();
                    xyFiniteDifference = (z_current_xy - z_previous_xy) / (x_diff * y_diff);

                } else {
                    double x_previous = grid[row - 1][column].x();
                    double x_next = grid[row + 1][column].x();
                    double x_diff = x_next - x_previous;

                    double y_previous = grid[row][column - 1].y();
                    double y_next = grid[row][column + 1].y();
                    double y_diff = y_next - y_previous;

                    double z_previous_xy = grid[row - 1][column - 1].z();
                    double z_next_x_previous_y = grid[row + 1][column - 1].z();
                    double z_next_y_previous_x = grid[row - 1][column + 1].z();
                    double z_next_xy = grid[row + 1][column + 1].z();

                    xyFiniteDifference = (z_next_xy - z_next_x_previous_y - z_next_y_previous_x + z_previous_xy) / (4 * x_diff * y_diff);
                }

                xyDerivativeGrid[row][column] = xyFiniteDifference;
            }
        }
        return xyDerivativeGrid;
    }

    @Override
    public double getFunctionValue(double x, double y) {
        var patch = patches.stream()
                .filter(p -> p.isInPatch(x, y))
                .findFirst()
                .orElseThrow();

        return patch.getFunctionValue(x, y);
    }

    private static class BiCubicPatch {
        private static final RealMatrix H = new Array2DRowRealMatrix(new double[][]{
                {2, -2, 1, 1},
                {-3, 3, -2, -1},
                {0, 0, 1, 0},
                {1, 0, 0, 0}
        });
        private static final RealMatrix HT = H.transpose();
        private final RealMatrix coefficients;
        private final Interval xInterval;
        private final Interval yInterval;

        public BiCubicPatch(Interval xInterval,
                            Interval yInterval,
                            double[][] coefficients) {
            this.xInterval = xInterval;
            this.yInterval = yInterval;
            var P = new Array2DRowRealMatrix(coefficients);
            this.coefficients = H.multiply(P.multiply(HT));
        }

        public double getFunctionValue(double x, double y) {
            double u = scaleValue(x, xInterval.startBoundaryPoint(), xInterval.endBoundaryPoint(), 0, 1);
            double v = scaleValue(y, yInterval.startBoundaryPoint(), yInterval.endBoundaryPoint(), 0, 1);
            RealVector U = createVectorU(u);
            RealVector V = createVectorV(v);
            return U.dotProduct(coefficients.operate(V));
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
