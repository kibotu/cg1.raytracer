package cg2.scenegraph.primitives;

import cg2.math.Homogeneous3DVector_Double;
import cg2.math.Homogeneous3DVector_Double_Math;
import cg2.math.M4xN1_Matrix_Double;
import cg2.math.M4xN1_Matrix_Double_Math;
import cg2.raytracer.Hit;
import cg2.raytracer.Ray;
import cg2.scenegraph.ADefaultSceneObject;
import cg2.util.IMaterial;
import cg2.util.Material;
import cg2.util.RGBAColor;

public class Triangle extends ADefaultSceneObject implements ITriangle {

    /**
     * the first edge of the triangle
     */
    private final Homogeneous3DVector_Double vector1;

    /**
     * the second edge of the triangle
     */
    private final Homogeneous3DVector_Double vector2;

    /**
     * the third edge of the triangle
     */
    private final Homogeneous3DVector_Double vector3;

    /**
     * the triangles material
     */
    private final Material material;

    /**
     * a complete constructor
     *
     * @param vector1  the first edge of the triangle
     * @param vector2  the second edge of the triangle
     * @param vector3  the third edge of the triangle
     * @param material the triangles material
     */
    public Triangle(final Homogeneous3DVector_Double vector1, final Homogeneous3DVector_Double vector2, final Homogeneous3DVector_Double vector3, final Material material) {
        this.vector1 = vector1;
        this.vector2 = vector2;
        this.vector3 = vector3;
        this.material = material;
    }

    /**
     * a complete constructor
     *
     * @param vector1X the x value of the first edge of the triangle
     * @param vector1Y the y value of the first edge of the triangle
     * @param vector1Z the z value of the first edge of the triangle
     * @param vector2X the x value of the second edge of the triangle
     * @param vector2Y the y value of the second edge of the triangle
     * @param vector2Z the z value of the second edge of the triangle
     * @param vector3X the x value of the third edge of the triangle
     * @param vector3Y the y value of the third edge of the triangle
     * @param vector3Z the z value of the third edge of the triangle
     * @param material the triangles material
     */
    public Triangle(final double vector1X, final double vector1Y, final double vector1Z, final double vector2X, final double vector2Y, final double vector2Z, final double vector3X, final double vector3Y, final double vector3Z, final Material material) {
        this(new Homogeneous3DVector_Double(vector1X, vector1Y, vector1Z, 1), new Homogeneous3DVector_Double(vector2X, vector2Y, vector2Z, 1), new Homogeneous3DVector_Double(vector3X, vector3Y, vector3Z, 1), material);
    }

    /**
     * a customized constructor
     * missing values will be set randomly
     *
     * @param vector1 the first edge of the triangle
     * @param vector2 the second edge of the triangle
     * @param vector3 the third edge of the triangle
     */
    public Triangle(final Homogeneous3DVector_Double vector1, final Homogeneous3DVector_Double vector2, final Homogeneous3DVector_Double vector3) {
        this(vector1, vector2, vector3, new Material());
    }

    /**
     * a customized constructor
     * missing values will be set randomly
     *
     * @param vector1X the x value of the first edge of the triangle
     * @param vector1Y the y value of the first edge of the triangle
     * @param vector1Z the z value of the first edge of the triangle
     * @param vector2X the x value of the second edge of the triangle
     * @param vector2Y the y value of the second edge of the triangle
     * @param vector2Z the z value of the second edge of the triangle
     * @param vector3X the x value of the third edge of the triangle
     * @param vector3Y the y value of the third edge of the triangle
     * @param vector3Z the z value of the third edge of the triangle
     */
    public Triangle(final double vector1X, final double vector1Y, final double vector1Z, final double vector2X, final double vector2Y, final double vector2Z, final double vector3X, final double vector3Y, final double vector3Z) {
        this(new Homogeneous3DVector_Double(vector1X, vector1Y, vector1Z, 1), new Homogeneous3DVector_Double(vector2X, vector2Y, vector2Z, 1), new Homogeneous3DVector_Double(vector3X, vector3Y, vector3Z, 1), new Material());
    }

    @Override
    public RGBAColor getColor() {
        return this.material.getMaterialColor();
    }

    @Override
    public IMaterial getMaterial() {
        return this.material;
    }

    @Override
    public Homogeneous3DVector_Double calculateNormalAtPosition(final Homogeneous3DVector_Double position) {
        Homogeneous3DVector_Double v1 = Homogeneous3DVector_Double_Math.subtract(vector2, vector1);
        Homogeneous3DVector_Double v2 = Homogeneous3DVector_Double_Math.subtract(vector3, vector1);
        Homogeneous3DVector_Double normal = Homogeneous3DVector_Double_Math.crossProduct(v1, v2).normalize();
        return normal;
    }

    /**
     * the mathematically basics of this method are from Markus Trenkwalders Bakkalaureatsarbeit "Implementierung eines Raytracers"
     */
    @Override
    public Hit intersect(Ray potentialHittingRay) {

        Homogeneous3DVector_Double rayOrigin = potentialHittingRay.getOrigin();
        Homogeneous3DVector_Double rayDirection = potentialHittingRay.getDirection();


        Homogeneous3DVector_Double e1 = Homogeneous3DVector_Double_Math.subtract(vector2, vector1);
        Homogeneous3DVector_Double e2 = Homogeneous3DVector_Double_Math.subtract(vector3, vector1);
        Homogeneous3DVector_Double s = Homogeneous3DVector_Double_Math.subtract(rayOrigin, vector1);
        Homogeneous3DVector_Double p = Homogeneous3DVector_Double_Math.crossProduct(rayDirection, e2);
        Homogeneous3DVector_Double q = Homogeneous3DVector_Double_Math.crossProduct(s, e1);

        M4xN1_Matrix_Double resultVector = M4xN1_Matrix_Double_Math.scalarMultiplicate(1 / M4xN1_Matrix_Double_Math.scalarProdukt(p, e1), new M4xN1_Matrix_Double(M4xN1_Matrix_Double_Math.scalarProdukt(q, e2), M4xN1_Matrix_Double_Math.scalarProdukt(p, s), M4xN1_Matrix_Double_Math.scalarProdukt(q, rayDirection), 0));
        double t = resultVector.getValueAt(0, 0);
        double u = resultVector.getValueAt(1, 0);
        double v = resultVector.getValueAt(2, 0);

        if (u >= 0 && v >= 0 && u + v <= 1) {
            Homogeneous3DVector_Double hitPosition = Homogeneous3DVector_Double_Math.add(potentialHittingRay.getOrigin(), Homogeneous3DVector_Double_Math.scalarMultiplicate(t, potentialHittingRay.getDirection()));
            Homogeneous3DVector_Double hitNormal = calculateNormalAtPosition(hitPosition);
            if (Homogeneous3DVector_Double_Math.scalarProdukt(Homogeneous3DVector_Double_Math.scalarMultiplicate(-1, rayDirection), hitNormal) < 0) {
                return new Hit(this, potentialHittingRay, t, hitPosition, Homogeneous3DVector_Double_Math.scalarMultiplicate(-1, hitNormal));
            }
            return new Hit(this, potentialHittingRay, t, hitPosition, hitNormal);
        }

        return null;
    }

}
