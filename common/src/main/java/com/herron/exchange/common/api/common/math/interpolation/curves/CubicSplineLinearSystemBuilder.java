package com.herron.exchange.common.api.common.math.interpolation.curves;

import com.herron.exchange.common.api.common.math.model.Interval2d;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.List;

import static java.lang.Math.pow;

public class CubicSplineLinearSystemBuilder {
    public static final int NR_OF_COEFFICIENTS_IN_CUBIC_POLYNOMIAL = 4;
    private final List<Interval2d> boundaryPoints;
    private final int nrOfMatrixColumns;

    public CubicSplineLinearSystemBuilder(List<Interval2d> boundaryPoints) {
        this.boundaryPoints = boundaryPoints;
        this.nrOfMatrixColumns = boundaryPoints.size() * NR_OF_COEFFICIENTS_IN_CUBIC_POLYNOMIAL;
    }

    public RealMatrix createCoefficientMatrix() {
        RealMatrix firstCondition = firstConditionPolynomialPassesThroughRespectiveBoundaryPoints();
        RealMatrix secondCondition = secondConditionFirstDerivativeMatchInteriorPoints();
        RealMatrix thirdCondition = thirdConditionSecondDerivativeMatchInteriorPoints();
        RealMatrix fourthCondition = fourthConditionSecondDerivativeVanishingEndPoints();
        return concatMatrix(firstCondition, secondCondition, thirdCondition, fourthCondition);
    }

    private RealMatrix firstConditionPolynomialPassesThroughRespectiveBoundaryPoints() {
        RealMatrix matrix = new Array2DRowRealMatrix(boundaryPoints.size() * 2, nrOfMatrixColumns);
        for (int i = 0; i < boundaryPoints.size(); i++) {
            final Interval2d boundaryPoint = boundaryPoints.get(i);
            int rowIndex = i * 2;
            int columnShift = NR_OF_COEFFICIENTS_IN_CUBIC_POLYNOMIAL * i;
            addBoundaryPoint(matrix, rowIndex, columnShift, boundaryPoint.startBoundaryPointX());
            addBoundaryPoint(matrix, rowIndex + 1, columnShift, boundaryPoint.endBoundaryPointX());
        }
        return matrix;
    }

    private void addBoundaryPoint(final RealMatrix matrix, final int rowIndex, final int columnShift, double boundaryPoint) {
        for (int columnIndex = columnShift; columnIndex < columnShift + NR_OF_COEFFICIENTS_IN_CUBIC_POLYNOMIAL; columnIndex++) {
            matrix.addToEntry(rowIndex, columnIndex, pow(boundaryPoint, 3.0 - columnIndex + columnShift));
        }
    }

    private RealMatrix secondConditionFirstDerivativeMatchInteriorPoints() {
        RealMatrix matrix = new Array2DRowRealMatrix(boundaryPoints.size() - 1, nrOfMatrixColumns);
        for (int i = 0; i < boundaryPoints.size() - 1; i++) {
            final Interval2d boundaryPoint = boundaryPoints.get(i);
            int rowIndex = i;
            int columnShift = NR_OF_COEFFICIENTS_IN_CUBIC_POLYNOMIAL * i;
            addFirstDerivativeInteriorPoint(matrix, rowIndex, columnShift, boundaryPoint.endBoundaryPointX());
        }
        return matrix;
    }

    private void addFirstDerivativeInteriorPoint(final RealMatrix matrix, final int rowIndex, final int columnShift, final double boundaryPoint) {
        for (int columnIndex = columnShift; columnIndex < columnShift + NR_OF_COEFFICIENTS_IN_CUBIC_POLYNOMIAL; columnIndex++) {
            double entryValue = (3.0 - columnIndex + columnShift) * pow(boundaryPoint, 2.0 - columnIndex + columnShift);
            matrix.addToEntry(rowIndex, columnIndex, entryValue);
            matrix.addToEntry(rowIndex, columnIndex + NR_OF_COEFFICIENTS_IN_CUBIC_POLYNOMIAL, -entryValue);
        }
    }

    private RealMatrix thirdConditionSecondDerivativeMatchInteriorPoints() {
        RealMatrix matrix = new Array2DRowRealMatrix(boundaryPoints.size() - 1, nrOfMatrixColumns);
        for (int i = 0; i < boundaryPoints.size() - 1; i++) {
            final Interval2d boundaryPoint = boundaryPoints.get(i);
            int rowIndex = i;
            int columnShift = NR_OF_COEFFICIENTS_IN_CUBIC_POLYNOMIAL * i;
            addSecondDerivativeInteriorPoint(matrix, rowIndex, columnShift, boundaryPoint.endBoundaryPointX());
        }
        return matrix;
    }

    private void addSecondDerivativeInteriorPoint(final RealMatrix matrix, final int rowIndex, final int columnShift, final double boundaryPoint) {
        double firstEntryValue = 6.0 * boundaryPoint;
        matrix.addToEntry(rowIndex, columnShift, firstEntryValue);
        matrix.addToEntry(rowIndex, columnShift + NR_OF_COEFFICIENTS_IN_CUBIC_POLYNOMIAL, -firstEntryValue);

        double secondEntryValue = 2.0;
        matrix.addToEntry(rowIndex, columnShift + 1, secondEntryValue);
        matrix.addToEntry(rowIndex, columnShift + NR_OF_COEFFICIENTS_IN_CUBIC_POLYNOMIAL + 1, -secondEntryValue);
    }

    private RealMatrix fourthConditionSecondDerivativeVanishingEndPoints() {
        RealMatrix matrix = new Array2DRowRealMatrix(2, nrOfMatrixColumns);
        double startPoint = boundaryPoints.get(0).startBoundaryPointX();
        matrix.addToEntry(0, 0, 6 * startPoint);
        matrix.addToEntry(0, 1, 2);

        double endPoint = boundaryPoints.get(boundaryPoints.size() - 1).endBoundaryPointX();
        matrix.addToEntry(1, nrOfMatrixColumns - 4, 6 * endPoint);
        matrix.addToEntry(1, nrOfMatrixColumns - 3, 2);
        return matrix;
    }

    private RealMatrix concatMatrix(final RealMatrix firstCondition, final RealMatrix secondCondition,
                                    final RealMatrix thirdCondition, final RealMatrix fourthCondition) {
        final RealMatrix matrix = new Array2DRowRealMatrix(firstCondition.getRowDimension() +
                secondCondition.getRowDimension() + thirdCondition.getRowDimension() + fourthCondition.getRowDimension(),
                nrOfMatrixColumns);

        matrix.setSubMatrix(firstCondition.getData(), 0, 0);
        matrix.setSubMatrix(secondCondition.getData(), firstCondition.getRowDimension(), 0);
        matrix.setSubMatrix(thirdCondition.getData(), firstCondition.getRowDimension() + secondCondition.getRowDimension(), 0);
        matrix.setSubMatrix(fourthCondition.getData(), firstCondition.getRowDimension() + secondCondition.getRowDimension() + thirdCondition.getRowDimension(), 0);
        return matrix;
    }

    public RealVector createConstantVector() {
        final RealVector vector = new ArrayRealVector(nrOfMatrixColumns);
        addFirstConditionConstants(vector);
        return vector;
    }

    private void addFirstConditionConstants(final RealVector vector) {
        for (int i = 0; i < boundaryPoints.size(); i++) {
            Interval2d boundaryPoint = boundaryPoints.get(i);
            int rowIndex = i * 2;
            vector.addToEntry(rowIndex, boundaryPoint.startBoundaryPointY());
            vector.addToEntry(rowIndex + 1, boundaryPoint.endBoundaryPointY());
        }
    }
}
