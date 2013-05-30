package cg2.math;


public class MXxN1_Matrix_Double extends MXxNX_Matrix_Double {

    public MXxN1_Matrix_Double(final double[][] matrixValues) {
        super(matrixValues);
        if (this.n != 1) {
            throw (new IllegalArgumentException("The specified matrix has more than one column."));
        }
    }

    public MXxN1_Matrix_Double(final MXxNX_Matrix_Double matrix) {
        super(matrix);
        if (this.n != 1) {
            throw (new IllegalArgumentException("The specified matrix has more than one column."));
        }
    }

    public MXxN1_Matrix_Double(final double... matrixValues) {
        super(matrixValues.length, 1, matrixValues);
    }

}
