package com.herron.exchange.common.api.common.math.interpolation.surfaces;

import com.herron.exchange.common.api.common.api.math.CartesianPoint3d;
import com.herron.exchange.common.api.common.api.math.Function3d;
import com.herron.exchange.common.api.common.math.model.Interval;
import org.apache.commons.math3.linear.*;

import java.util.*;

import static com.herron.exchange.common.api.common.math.MathUtils.scaleValue;

public class NurbsLeastSquaresSurface implements Function3d {

    private final Interval xInterval;
    private final Interval yInterval;
    private final RealVector knotVectorU;
    private final RealVector knotVectorV;
    private final RealMatrix controlPoints;
    private final int nrOfControlPointsU;
    private final int nrOfControlPointsV;
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
        this.nrOfControlPointsU = xValueToPoints.size() / 2;
        this.nrOfControlPointsV = yValueToPoints.size() / 2;
        this.knotVectorU = buildKnotVector(nrOfControlPointsU - 1);
        this.knotVectorV = buildKnotVector(nrOfControlPointsV - 1);
        this.controlPoints = buildControlPoints(xValueToPoints, yValueToPoints);
    }

    private RealVector buildKnotVector(int n) {
        int maxIndex = n + degree + 1;
        RealVector knots = new ArrayRealVector(maxIndex + 1);
        for (int i = 0; i <= maxIndex; i++) {
            double value;
            if (i <= degree) {
                value = 0;
            } else if (degree + 1 <= i && i <= n) {
                value = (i - degree) / (n + 1.0 - degree);
            } else {
                value = 1;
            }
            knots.addToEntry(i, value);
        }
        return knots;
    }

    private RealMatrix buildControlPoints(NavigableMap<Double, List<CartesianPoint3d>> xValueToPoints,
                                          NavigableMap<Double, List<CartesianPoint3d>> yValueToPoints) {
        RealMatrix P = buildPointMatrix(xValueToPoints, yValueToPoints);
        RealMatrix A = buildMatrixKnot(xValueToPoints.keySet(), knotVectorU, xInterval, nrOfControlPointsU - 1);
        RealMatrix AT = A.transpose();
        RealMatrix B = buildMatrixKnot(yValueToPoints.keySet(), knotVectorV, yInterval, nrOfControlPointsV - 1);
        RealMatrix BT = B.transpose();

        CholeskyDecomposition aChol = new CholeskyDecomposition(AT.multiply(A));
        RealMatrix X = aChol.getSolver().getInverse().multiply(AT);
        CholeskyDecomposition bChol = new CholeskyDecomposition(BT.multiply(B));
        RealMatrix Y = bChol.getSolver().getInverse().multiply(BT);
        return X.multiply(P).multiply(Y.transpose());
    }

    private RealMatrix buildPointMatrix(NavigableMap<Double, List<CartesianPoint3d>> xValueToPoints,
                                        NavigableMap<Double, List<CartesianPoint3d>> yValueToPoints) {

        RealMatrix matrix = new Array2DRowRealMatrix(xValueToPoints.size(), yValueToPoints.size());
        int row = 0;
        for (var entry : xValueToPoints.entrySet()) {
            List<CartesianPoint3d> pointsAtX = entry.getValue();
            pointsAtX.sort(Comparator.comparingDouble(CartesianPoint3d::y));
            int column = 0;
            for (var point : pointsAtX) {
                matrix.addToEntry(row, column, point.z());
                column++;
            }
            row++;
        }
        return matrix;
    }

    private RealMatrix buildMatrixKnot(Collection<Double> values,
                                       RealVector knotVector,
                                       Interval interval,
                                       int n) {
        Map<BasisKey, Double> keyToValue = new HashMap<>();
        var matrix = new Array2DRowRealMatrix(values.size(), n + 1);
        int row = 0;
        for (var value : values) {
            double t = scaleValue(value, interval.startBoundaryPoint(), interval.endBoundaryPoint(), 0, 1);
            for (int column = 0; column <= n; column++) {
                double basisValue = calculateBasisValue(keyToValue, knotVector, column, degree, t, n);
                matrix.addToEntry(row, column, basisValue);
            }
            row++;
        }
        return matrix;
    }

    public static NurbsLeastSquaresSurface create(List<CartesianPoint3d> points, int degree) {
        return new NurbsLeastSquaresSurface(points, degree);
    }

    @Override
    public double getFunctionValue(double x, double y) {
        Map<BasisKey, Double> keyToValueU = new HashMap<>();
        Map<BasisKey, Double> keyToValueV = new HashMap<>();
        double u = scaleValue(x, xInterval.startBoundaryPoint(), xInterval.endBoundaryPoint(), 0, 1);
        double v = scaleValue(y, yInterval.startBoundaryPoint(), yInterval.endBoundaryPoint(), 0, 1);
        double value = 0;
        for (int row = 0; row < controlPoints.getRowDimension(); row++) {
            for (int column = 0; column < controlPoints.getColumnDimension(); column++) {
                value += calculateBasisValue(keyToValueU, knotVectorU, row, degree, u, nrOfControlPointsU - 1) *
                        calculateBasisValue(keyToValueV, knotVectorV, column, degree, v, nrOfControlPointsV - 1) *
                        controlPoints.getEntry(row, column);
            }
        }
        return value;
    }

    private double calculateBasisValue(Map<BasisKey, Double> keyToValue, RealVector knotVector, int i, int j, double t, int n) {
        var key = new BasisKey(i, j, t);
        if (keyToValue.containsKey(key)) {
            return keyToValue.get(key);
        }

        double t_i = knotVector.getEntry(i);

        if (j == 0) {
            double t_i_1 = knotVector.getEntry(i + 1);
            double value = t_i <= t && t < t_i_1 ? 1 : 0;
            return keyToValue.computeIfAbsent(key, k -> value);
        }

        if ((1 <= j && j <= degree) && (0 <= i && i <= n + degree - j)) {
            double t_i_1 = knotVector.getEntry(i + 1);
            double t_i_j = knotVector.getEntry(i + j);
            double t_i_j_1 = knotVector.getEntry(i + j + 1);

            double firstTermBasisValue = getBasisValue(keyToValue, knotVector, i, j - 1, t, n);
            double firstTerm = firstTermBasisValue == 0 ? 0 : ((t - t_i) / (t_i_j - t_i)) * firstTermBasisValue;

            double secondTermBasisValue = getBasisValue(keyToValue, knotVector, i + 1, j - 1, t, n);
            double secondTerm = secondTermBasisValue == 0 ? 0 : ((t_i_j_1 - t) / (t_i_j_1 - t_i_1)) * secondTermBasisValue;

            return keyToValue.computeIfAbsent(key, k -> firstTerm + secondTerm);
        }

        return 0;
    }

    private double getBasisValue(Map<BasisKey, Double> keyToValue, RealVector knotVector, int i, int j, double t, int n) {
        var key = new BasisKey(i, j, t);
        if (keyToValue.containsKey(key)) {
            return keyToValue.get(key);
        } else {
            return calculateBasisValue(keyToValue, knotVector, i, j, t, n);
        }
    }

    private record BasisKey(int i, int j, double t) {

    }
}
