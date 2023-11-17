package com.herron.exchange.common.api.common.math.interpolation.surfaces;

import com.herron.exchange.common.api.common.api.math.CartesianPoint3d;
import com.herron.exchange.common.api.common.api.math.Function3d;
import com.herron.exchange.common.api.common.math.model.Interval;
import com.herron.exchange.common.api.common.math.model.Point3d;
import org.apache.commons.math3.linear.*;

import java.util.*;

import static com.herron.exchange.common.api.common.math.MathUtils.scaleValue;

public class NurbsLeastSquaresSurface implements Function3d {

    private final Interval xInterval;
    private final Interval yInterval;
    private final RealVector knotVectorU;
    private final RealVector knotVectorV;
    private final RealMatrix controlPoints;
    private final int degree;

    private NurbsLeastSquaresSurface(List<CartesianPoint3d> points, int degree) {
        this.degree = degree;

        NavigableMap<Double, List<CartesianPoint3d>> xValueToPoints = new TreeMap<>();
        NavigableMap<Double, List<CartesianPoint3d>> yValueToPoints = new TreeMap<>();
        for (var point : points) {
            xValueToPoints.computeIfAbsent(point.x(), k -> new ArrayList<>()).add(point);
            yValueToPoints.computeIfAbsent(point.y(), k -> new ArrayList<>()).add(point);
        }

        this.xInterval = new Interval(xValueToPoints.firstKey(), xValueToPoints.lastKey());
        this.yInterval = new Interval(yValueToPoints.firstKey(), yValueToPoints.lastKey());
        this.controlPoints = buildControlPoints(xValueToPoints, xValueToPoints.size(), yValueToPoints.size());
        this.knotVectorU = buildKnotVector(xValueToPoints.size());
        this.knotVectorV = buildKnotVector(yValueToPoints.size());
    }

    private RealVector buildKnotVector(int n) {
        int nrOfKnots = n + degree + 1;
        RealVector knots = new ArrayRealVector(nrOfKnots);
        for (int i = 0; i < nrOfKnots; i++) {
            double value;
            if (i <= degree) {
                value = 0;
            } else if (degree + 1 <= i && i <= n) {
                value = (i - degree) / (n + 1.0 + degree);
            } else {
                value = 1;
            }
            knots.addToEntry(i, value);
        }
        return knots;
    }

    private RealMatrix buildControlPoints(NavigableMap<Double, List<CartesianPoint3d>> xValueToPoints,
                                          int nrOfX,
                                          int nrOfY) {
        List<List<CartesianPoint3d>> grid = buildGrid(xValueToPoints);
        RealMatrix P = buildPointMatrix(grid, nrOfX, nrOfY);
        RealMatrix A = buildMatrixA(grid, nrOfX);
        RealMatrix AT = A.transpose();
        RealMatrix B = buildMatrixA();
        RealMatrix BT = B.transpose();

        LUDecomposition aLu = new LUDecomposition(AT.multiply(A));
        RealMatrix X = aLu.getSolver().getInverse().multiply(AT);
        LUDecomposition bLu = new LUDecomposition(BT.multiply(B));
        RealMatrix Y = bLu.getSolver().getInverse().multiply(BT);
        return X.multiply(P).multiply(Y.transpose());
    }

    private List<List<CartesianPoint3d>> buildGrid(NavigableMap<Double, List<CartesianPoint3d>> xValueToPoints) {
        List<List<CartesianPoint3d>> grid = new ArrayList<>();
        for (var entry : xValueToPoints.entrySet()) {
            List<CartesianPoint3d> pointsAtX = new ArrayList<>();
            for (var point : entry.getValue()) {
                pointsAtX.add(new Point3d(
                        scaleValue(point.x(), xInterval.startBoundaryPoint(), xInterval.endBoundaryPoint(), 0, 1),
                        scaleValue(point.y(), yInterval.startBoundaryPoint(), yInterval.endBoundaryPoint(), 0, 1),
                        point.z()
                ));
            }
            grid.add(pointsAtX);
        }
        return grid;
    }

    private RealMatrix buildPointMatrix(List<List<CartesianPoint3d>> grid,
                                        int nrOfX,
                                        int nrOfY) {

        RealMatrix matrix = new Array2DRowRealMatrix(nrOfX, nrOfY);
        int row = 0;
        for (var pointsAtx : grid) {
            int column = 0;
            for (var point : pointsAtx) {
                matrix.addToEntry(row, column, point.z());
                column++;
            }
            row++;
        }
        return matrix;
    }

    private RealMatrix buildMatrixA(Collection<Double> xValues) {
        var matrix = new Array2DRowRealMatrix(xValues.size(), knotVectorU.getDimension());
        int row = 0;
        Map<BasisKey, Double> keyToValue = new HashMap<>();
        for (var x : xValues) {
            double u = scaleValue(x, xInterval.startBoundaryPoint(), xInterval.endBoundaryPoint(), 0, 1);
            for (int column = 0; column < knotVectorU.getDimension(); column++) {
                double value = calculateBasisValue(keyToValue, knotVectorU, column, degree, u);
                matrix.addToEntry(row, column, value);
            }
        }
    }

    public static NurbsLeastSquaresSurface create(List<CartesianPoint3d> points, int degree) {
        return new NurbsLeastSquaresSurface(points, degree);
    }

    @Override
    public double getFunctionValue(double x, double y) {
        Map<BasisKey, Double> keyToValue = new HashMap<>();
        double u = scaleValue(x, xInterval.startBoundaryPoint(), xInterval.endBoundaryPoint(), 0, 1);
        double v = scaleValue(y, yInterval.startBoundaryPoint(), yInterval.endBoundaryPoint(), 0, 1);
        double value = 0;
        for (int row = 0; row < controlPoints.getRowDimension(); row++) {
            for (int column = 0; column < controlPoints.getColumnDimension(); column++) {
                value = calculateBasisValue(keyToValue, knotVectorU, row, degree, u) *
                        calculateBasisValue(keyToValue, knotVectorV, column, degree, v) *
                        controlPoints.getEntry(row, column);
            }
        }
        return value;
    }

    private double calculateBasisValue(Map<BasisKey, Double> keyToValue, RealVector knotVector, int i, int j, double t) {
        var key = new BasisKey(i, j, t);
        if (keyToValue.containsKey(key)) {
            return keyToValue.get(key);
        }

        double t_i = knotVector.getEntry(i);
        double t_i_1 = knotVector.getEntry(i + 1);
        double t_i_j = knotVector.getEntry(i + j);
        double t_i_j_1 = knotVector.getEntry(i + j + 1);

        if (degree == 0) {
            double value = t_i <= t && t < t_i_1 ? 1 : 0;
            keyToValue.putIfAbsent(new BasisKey(i, j, t), value);
            return value;
        }

        double firstTerm = ((t - t_i) / (t_i_j - t_i)) * keyToValue.putIfAbsent(new BasisKey(i, j - 1, t), calculateBasisValue(keyToValue, i, j - 1, t));
        double secondTerm = ((t_i_j_1 - t) / (t_i_j_1 - t_i_1)) * keyToValue.putIfAbsent(new BasisKey(i + 1, j - 1, t), calculateBasisValue(keyToValue, i + 1, j - 1, t));

        return keyToValue.putIfAbsent(key, firstTerm + secondTerm);
    }

    private record BasisKey(int i, int j, double t) {

    }
}
