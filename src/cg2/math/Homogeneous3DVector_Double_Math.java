package cg2.math;

/**
 * @author Albert
 *         <p/>
 *         some Matrix operations optimized for homogeneous 3D vectors
 */
public class Homogeneous3DVector_Double_Math {

    public static Homogeneous3DVector_Double crossProduct(Homogeneous3DVector_Double matrix1, Homogeneous3DVector_Double matrix2) {
        return new Homogeneous3DVector_Double(matrix1.getValueAt(1, 0) * matrix2.getValueAt(2, 0) - matrix1.getValueAt(2, 0) * matrix2.getValueAt(1, 0), -matrix1.getValueAt(0, 0) * matrix2.getValueAt(2, 0) + matrix1.getValueAt(2, 0) * matrix2.getValueAt(0, 0), matrix1.getValueAt(0, 0) * matrix2.getValueAt(1, 0) - matrix1.getValueAt(1, 0) * matrix2.getValueAt(0, 0), 0);
    }

    public static Homogeneous3DVector_Double scalarMultiplicate(double scalar, Homogeneous3DVector_Double matrix) {
        return new Homogeneous3DVector_Double(scalar * matrix.getValueAt(0, 0), scalar * matrix.getValueAt(1, 0), scalar * matrix.getValueAt(2, 0), scalar * matrix.getValueAt(3, 0));
    }

    public static double scalarProdukt(Homogeneous3DVector_Double matrix1, Homogeneous3DVector_Double matrix2) {
        return matrix1.getValueAt(0, 0) * matrix2.getValueAt(0, 0) + matrix1.getValueAt(1, 0) * matrix2.getValueAt(1, 0) + matrix1.getValueAt(2, 0) * matrix2.getValueAt(2, 0) + matrix1.getValueAt(3, 0) * matrix2.getValueAt(3, 0);
    }

    public static Homogeneous3DVector_Double add(Homogeneous3DVector_Double matrix1, Homogeneous3DVector_Double matrix2) {
        return new Homogeneous3DVector_Double(matrix1.getValueAt(0, 0) + matrix2.getValueAt(0, 0), matrix1.getValueAt(1, 0) + matrix2.getValueAt(1, 0), matrix1.getValueAt(2, 0) + matrix2.getValueAt(2, 0), matrix1.getValueAt(3, 0) + matrix2.getValueAt(3, 0));
    }

    public static Homogeneous3DVector_Double subtract(Homogeneous3DVector_Double matrix1, Homogeneous3DVector_Double matrix2) {
        return new Homogeneous3DVector_Double(matrix1.getValueAt(0, 0) - matrix2.getValueAt(0, 0), matrix1.getValueAt(1, 0) - matrix2.getValueAt(1, 0), matrix1.getValueAt(2, 0) - matrix2.getValueAt(2, 0), matrix1.getValueAt(3, 0) - matrix2.getValueAt(3, 0));
    }
}
