package cg2.math;

/**
 * @author Albert
 *         <p/>
 *         some matrix operations optimized for 4x1 matrixes
 */
public class M4xN1_Matrix_Double_Math {

    public static M4xN1_Matrix_Double scalarMultiplicate(double scalar, M4xN1_Matrix_Double matrix) {
        return new M4xN1_Matrix_Double(scalar * matrix.getValueAt(0, 0), scalar * matrix.getValueAt(1, 0), scalar * matrix.getValueAt(2, 0), scalar * matrix.getValueAt(3, 0));
    }

    public static double scalarProdukt(M4xN1_Matrix_Double matrix1, M4xN1_Matrix_Double matrix2) {
        return matrix1.getValueAt(0, 0) * matrix2.getValueAt(0, 0) + matrix1.getValueAt(1, 0) * matrix2.getValueAt(1, 0) + matrix1.getValueAt(2, 0) * matrix2.getValueAt(2, 0) + matrix1.getValueAt(3, 0) * matrix2.getValueAt(3, 0);
    }

    public static M4xN1_Matrix_Double subtract(M4xN1_Matrix_Double matrix1, M4xN1_Matrix_Double matrix2) {
        return new M4xN1_Matrix_Double(matrix1.getValueAt(0, 0) - matrix2.getValueAt(0, 0), matrix1.getValueAt(1, 0) - matrix2.getValueAt(1, 0), matrix1.getValueAt(2, 0) - matrix2.getValueAt(2, 0), matrix1.getValueAt(3, 0) - matrix2.getValueAt(3, 0));
    }

}
