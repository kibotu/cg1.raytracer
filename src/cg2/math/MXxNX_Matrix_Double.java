package cg2.math;


public class MXxNX_Matrix_Double {

    public static void main(String[] args) {
        MXxNX_Matrix_Double test1 = new MXxNX_Matrix_Double(3, 4, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        MXxNX_Matrix_Double test2 = new MXxNX_Matrix_Double(4, 3, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        MXxNX_Matrix_Double test3 = MXxNX_Matrix_Double_Math.multiplicate(test1, test2);
        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);
    }

    /**
     * the matrix values
     */
    protected double[][] matrixValues;

    /**
     * specifies the number of rows
     */
    protected int m;

    /**
     * specifies the number of columns
     */
    protected int n;

    public MXxNX_Matrix_Double(final double[][] matrixValues) {
        this.setMatrixValues(matrixValues);
    }

    public MXxNX_Matrix_Double(final MXxNX_Matrix_Double matrix) {
        this.setMatrixValues(matrix.getMatrixValues());
    }

    public MXxNX_Matrix_Double(final int m, final int n, final double... values) {
        if (m * n != values.length) {
            throw (new IllegalArgumentException("the sepcified matrix-size don't equate the number of values"));
        }
        final double[][] matrixValues = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrixValues[i][j] = values[j * m + i];
            }
        }
        this.m = m;
        this.n = n;
        this.matrixValues = matrixValues;
    }

    public int getNumberOfRows() {
        return m;
    }

    public int getNumberOfColumns() {
        return n;
    }

    public int getNumberofValues() {
        return m * n;
    }

    public double[][] getMatrixValues() {
        return matrixValues.clone();
    }

    public double[] getRow(final int rowNumber) {
        return matrixValues[rowNumber].clone();
    }

    public double[] getColumn(final int columnNumber) {
        double[] column = new double[m];
        for (int i = 0; i < m; i++) {
            column[i] = matrixValues[i][columnNumber];
        }
        return column;
    }

    public double getValueAt(final int rowNumber, final int columnNumber) {
        return matrixValues[rowNumber][columnNumber];
    }

    public void setMatrixValues(final double[][] newMatrixValues) {
        final int m = newMatrixValues.length;
        final int n = newMatrixValues[0].length;
        for (int i = 0; i < m; i++) {
            if (newMatrixValues[i].length != n) {
                throw new IllegalArgumentException("The row-arrays don't have all the same size.");
            }
        }
        this.m = m;
        this.n = n;
        this.matrixValues = newMatrixValues;
    }

    public void setRow(final int rowNumber, final double[] newRow) {
        if (newRow.length != this.n) {
            throw new IllegalArgumentException("The row length don't equate to the matrix size.");
        }
        this.matrixValues[rowNumber] = newRow;
    }

    public void setColumn(final int columnNumber, final double[] newColumn) {
        if (newColumn.length != this.n) {
            throw new IllegalArgumentException("The column length don't equate to the matrix size.");
        }
        for (int i = 0; i < m; i++) {
            this.matrixValues[i][columnNumber] = newColumn[i];
        }
    }

    public void setValueAt(final int rowNumber, final int columnNumber, final double newValue) {
        this.matrixValues[rowNumber][columnNumber] = newValue;
    }

    public String toString() {
        String temp = "_" + m + "x" + n + "-Matrix_\n";
        for (int i = 0; i < m; i++) {
            temp += "| ";
            for (int ii = 0; ii < n; ii++) {
                temp += String.format("%7.2f ", this.getValueAt(i, ii));
            }
            temp += " |\n";
        }
        return temp;
    }
}
