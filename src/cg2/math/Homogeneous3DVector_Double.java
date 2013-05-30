package cg2.math;

public class Homogeneous3DVector_Double extends M4xN1_Matrix_Double {

    private boolean isDirection;

    public Homogeneous3DVector_Double(final double[][] vectorValues) {
        super(vectorValues);
        final double vectorTyp = this.matrixValues[3][0];
        if (vectorTyp == 0.0) {
            isDirection = true;
        } else if (vectorTyp == 1.0) {
            isDirection = false;
        } else {
            throw (new IllegalArgumentException("The last value of the vector have to be 1 or 0."));
        }
    }

    public Homogeneous3DVector_Double(final double... vectorValues) {
        super(vectorValues);
        final double vectorTyp = this.matrixValues[3][0];
        if (vectorTyp == 0.0) {
            isDirection = true;
        } else if (vectorTyp == 1.0) {
            isDirection = false;
        } else {
            throw (new IllegalArgumentException("The last value of the vector have to be 1 or 0."));
        }
    }

    public Homogeneous3DVector_Double(final Homogeneous3DVector_Double homogeneous3DVector) {
        this(homogeneous3DVector.getColumn(0));
    }

    public boolean isDirection() {
        return isDirection;
    }

    /**
     * returns the value of the x-dimension
     *
     * @return the x-dimensional value
     */
    public double getX() {
        return this.getValueAt(0, 0);
    }

    /**
     * returns the value of the y-dimension
     *
     * @return the y-dimensional value
     */
    public double getY() {
        return this.getValueAt(1, 0);
    }

    /**
     * returns the value of the z-dimension
     *
     * @return the z-dimensional value
     */
    public double getZ() {
        return this.getValueAt(2, 0);
    }

    /**
     * returns the value which specifies the vector-type
     *
     * @return the value which specifies the vector-type
     */
    public double getVectorTypValue() {
        return this.getValueAt(3, 0);
    }

    /**
     * sets the x-value
     *
     * @param newX the new x-value
     */
    public void setX(final double newX) {
        this.setValueAt(0, 0, newX);
    }

    /**
     * sets the y-value
     *
     * @param newY the new y-value
     */
    public void setY(final double newY) {
        this.setValueAt(1, 0, newY);
    }

    /**
     * sets the Z-value of a vector
     *
     * @param newZ the new Z-value
     */
    public void setZ(final double newZ) {
        this.setValueAt(2, 0, newZ);
    }

    /**
     * sets the value which specifies the vector-type
     *
     * @param newVectorType the new vector-type-value
     */
    public void setVectorTypValue(final double newVectorType) {
        this.setValueAt(3, 0, newVectorType);
    }

    /**
     * sets the x- and y-value
     *
     * @param newX the new x-value
     * @param newY the new y-value
     */
    public void setXY(final double newX, final double newY) {
        setX(newX);
        setY(newY);
    }

    /**
     * sets the x- and z-value
     *
     * @param newX the new x-value
     * @param newZ the new z-value
     */
    public void setXZ(final double newX, final double newZ) {
        setX(newX);
        setZ(newZ);
    }

    /**
     * sets the y- and z-value
     *
     * @param newY the new y-value
     * @param newZ the new z-value
     */
    public void setYZ(final double newY, final double newZ) {
        setY(newY);
        setZ(newZ);
    }

    /**
     * sets the x-, y- and z-value
     *
     * @param newX the new x-value
     * @param newY the new y-value
     * @param newZ the new z-value
     */
    public void setXYZ(final double newX, final double newY, final double newZ) {
        setX(newX);
        setY(newY);
        setZ(newZ);
    }

    /**
     * calculates the length(the absolute value) of this vector
     *
     * @return the length(the absolute value) of this vector
     */
    public double calculateLength() {
        return Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2) + Math.pow(this.getZ(), 2));
    }

    /**
     * calculates the normalized vector of this vector
     *
     * @return the normalized vector
     */
    public Homogeneous3DVector_Double normalize() {
        double inverseLength = 1.0 / calculateLength();
        return new Homogeneous3DVector_Double(this.getX() * inverseLength, this.getY() * inverseLength, this.getZ() * inverseLength, this.getVectorTypValue());
    }

}
