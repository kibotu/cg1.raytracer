package cg2.math;


/**
 * @author Albert
 *         <p/>
 *         some matrix operations
 */
public class MXxNX_Matrix_Double_Math {

    /**
     * Don't execute this constructor
     */
    protected MXxNX_Matrix_Double_Math() {
        throw (new IllegalAccessError("This class has just static methods. There is no need to create an instance."));
    }


    public static MXxNX_Matrix_Double makeIdentitymatrix(int numberOfRowsAndColumns) {
        if (numberOfRowsAndColumns <= 0) {
            throw (new IllegalArgumentException("The specified size for the identity-matrix have to be over 0."));
        }
        final double[][] identityValues = new double[numberOfRowsAndColumns][numberOfRowsAndColumns];
        for (int i = 0; i < numberOfRowsAndColumns; i++) {
            identityValues[i][i] = 1.0;
        }
        return new MXxNX_Matrix_Double(identityValues);
    }

    public static MXxNX_Matrix_Double transpose(final MXxNX_Matrix_Double matrix) {
        final double[][] matrixValues = matrix.getMatrixValues();
        final double[][] tranzposedValues = new double[matrix.getNumberOfColumns()][matrix.getNumberOfRows()];
        for (int i = 0; i < matrix.getNumberOfRows(); i++) {
            for (int j = 0; j < matrix.getNumberOfColumns(); j++) {
                tranzposedValues[j][i] = matrixValues[i][j];
            }
        }
        return new MXxNX_Matrix_Double(tranzposedValues);
    }

    public static MXxNX_Matrix_Double scalarMultiplicate(Number scalar, MXxNX_Matrix_Double matrix) {
        final double[][] matrixValues = matrix.getMatrixValues();
        final double[][] resultValues = new double[matrix.getNumberOfRows()][matrix.getNumberOfColumns()];
        for (int i = 0; i < matrix.getNumberOfRows(); i++) {
            for (int j = 0; j < matrix.getNumberOfColumns(); j++) {
                resultValues[i][j] = scalar.doubleValue() * matrixValues[i][j];
            }
        }
        return new MXxNX_Matrix_Double(resultValues);
    }

    public static double scalarProdukt(MXxN1_Matrix_Double matrix1, MXxN1_Matrix_Double matrix2) {
        final double[] vector1 = matrix1.getColumn(0);
        final double[] vector2 = matrix2.getColumn(0);
        if (vector1.length != vector2.length) {
            throw (new IllegalArgumentException("The specified arrays have a different length but they need to have the same length."));
        }
        double result = 0;
        for (int i = 0; i < vector1.length; i++) {
            result += vector1[i] * vector2[i];
        }
        return result;
    }

    public static MXxNX_Matrix_Double multiplicate(MXxNX_Matrix_Double matrix1, MXxNX_Matrix_Double matrix2) {
        if (!checkSizes_N_equals_M(matrix1, matrix2)) {
            throw (new IllegalArgumentException("The number of columns of the first matrix don't equate to the number of rows of the second matrix"));
        }
        final int resultNumberOfRows = matrix1.getNumberOfRows();
        final int resultNumberOfColumns = matrix2.getNumberOfColumns();
        final double[][] resultValues = new double[resultNumberOfRows][resultNumberOfColumns];
        int numberOfValuesToAdd = matrix1.getNumberOfColumns();
        for (int i = 0; i < resultNumberOfColumns; i++) {
            final double[] columnValues = matrix2.getColumn(i);
            for (int j = 0; j < resultNumberOfRows; j++) {
                final double[] rowValues = matrix1.getRow(j);
                for (int k = 0; k < numberOfValuesToAdd; k++) {
                    resultValues[j][i] = resultValues[j][i] + rowValues[k] * columnValues[k];
                }
            }
        }
        return new MXxNX_Matrix_Double(resultValues);
    }

    public static MXxNX_Matrix_Double add(MXxNX_Matrix_Double summand1, MXxNX_Matrix_Double summand2) {
        if (!checkSizes_M_equals_M_and_N_equals_N(summand1, summand2)) {
            throw (new IllegalArgumentException("The specified matrices don't have the same dimensions"));
        }
        double[][] summand1Values = summand1.getMatrixValues();
        double[][] summand2Values = summand2.getMatrixValues();
        double[][] sumValues = new double[summand1.getNumberOfRows()][summand1.getNumberOfColumns()];
        for (int i = 0; i < summand1.getNumberOfRows(); i++) {
            for (int j = 0; j < summand1.getNumberOfColumns(); j++) {
                sumValues[i][j] = summand1Values[i][j] + summand2Values[i][j];
            }
        }
        return new MXxNX_Matrix_Double(sumValues);
    }

    public static MXxNX_Matrix_Double subtract(MXxNX_Matrix_Double summand1, MXxNX_Matrix_Double summand2) {
        if (!checkSizes_M_equals_M_and_N_equals_N(summand1, summand2)) {
            throw (new IllegalArgumentException("The specified matrices don't have the same dimensions"));
        }
        double[][] summand1Values = summand1.getMatrixValues();
        double[][] summand2Values = summand2.getMatrixValues();
        double[][] sumValues = new double[summand1.getNumberOfRows()][summand1.getNumberOfColumns()];
        for (int i = 0; i < summand1.getNumberOfRows(); i++) {
            for (int j = 0; j < summand1.getNumberOfColumns(); j++) {
                sumValues[i][j] = summand1Values[i][j] - summand2Values[i][j];
            }
        }
        return new MXxNX_Matrix_Double(sumValues);
    }

    public static boolean checkSizes_M_equals_M(MXxNX_Matrix_Double matrix1, MXxNX_Matrix_Double matrix2) {
        return matrix1.getNumberOfRows() == matrix2.getNumberOfRows();
    }

    public static boolean checkSizes_N_equals_N(MXxNX_Matrix_Double matrix1, MXxNX_Matrix_Double matrix2) {
        return matrix1.getNumberOfColumns() == matrix2.getNumberOfColumns();
    }

    public static boolean checkSizes_M_equals_N(MXxNX_Matrix_Double matrix1, MXxNX_Matrix_Double matrix2) {
        return matrix1.getNumberOfRows() == matrix2.getNumberOfColumns();
    }

    public static boolean checkSizes_N_equals_M(MXxNX_Matrix_Double matrix1, MXxNX_Matrix_Double matrix2) {
        return checkSizes_M_equals_N(matrix2, matrix1);
    }

    public static boolean checkSizes_M_equals_M_and_N_equals_N(MXxNX_Matrix_Double matrix1, MXxNX_Matrix_Double matrix2) {
        return checkSizes_M_equals_M(matrix1, matrix2) && checkSizes_N_equals_N(matrix1, matrix2);
    }

    public static boolean checkSizes_M_equals_N_and_N_equals_M(MXxNX_Matrix_Double matrix1, MXxNX_Matrix_Double matrix2) {
        return checkSizes_M_equals_N(matrix1, matrix2) && checkSizes_N_equals_M(matrix1, matrix2);
    }
}
