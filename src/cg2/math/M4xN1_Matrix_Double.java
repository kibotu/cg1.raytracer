package cg2.math;

public class M4xN1_Matrix_Double extends MXxN1_Matrix_Double {

    public M4xN1_Matrix_Double(final double[][] matrixValues) {
        super(matrixValues);
        if (this.m != 4) {
            throw (new IllegalArgumentException("The specified matrix has more or less than four rows."));
        }
    }

    public M4xN1_Matrix_Double(final MXxNX_Matrix_Double matrix) {
        super(matrix);
        if (this.m != 4) {
            throw (new IllegalArgumentException("The specified matrix has more or less than four rows."));
        }
    }

    public M4xN1_Matrix_Double(final double... matrixValues) {
        super(matrixValues);
        if (this.m != 4) {
            throw (new IllegalArgumentException("The specified matrix has more or less than four rows."));
        }
    }

}
