package cg2.scenegraph.primitives;

import cg2.math.Homogeneous3DVector_Double;
import cg2.math.Homogeneous3DVector_Double_Math;
import cg2.raytracer.Hit;
import cg2.raytracer.Ray;
import cg2.scenegraph.ADefaultSceneObject;
import cg2.util.IMaterial;
import cg2.util.Material;
import cg2.util.RGBAColor;

public class Plane extends ADefaultSceneObject implements IPlane {

    /**
     * the planes center (rotating-point of the plane)
     */
    private final Homogeneous3DVector_Double center;

    /**
     * the planes normal. It defines the planes outside. changing it will rotate the plane around the plane's center.
     */
    private final Homogeneous3DVector_Double normalVector;

    /**
     * the planes material
     */
    private final Material material;

    /**
     * complete constructor
     *
     * @param center       the plane's center (rotating-point of the plane)
     * @param normalVector the plane's normal. It defines the planes outside. Changing it will rotate the plane around the plane's center.
     * @param material     the plane's material
     */
    public Plane(final Homogeneous3DVector_Double center, final Homogeneous3DVector_Double normalVector, final Material material) {
        super();
        this.center = center;
        this.normalVector = normalVector.normalize();
        this.material = material;
    }

    /**
     * complete constructor
     *
     * @param centerX       the plane's center x-value (rotating-point of the plane)
     * @param centerY       the plane's center y-value (rotating-point of the plane)
     * @param centerZ       the plane's center z-value (rotating-point of the plane)
     * @param normalVectorX the plane's normal x-value. The normalVector defines the planes outside. Changing it will rotate the plane around the plane's center.
     * @param normalVectorY the plane's normal y-value. The normalVector defines the planes outside. Changing it will rotate the plane around the plane's center.
     * @param normalVectorZ the plane's normal z-value. The normalVector defines the planes outside. Changing it will rotate the plane around the plane's center.
     * @param material      the plane's material
     */
    public Plane(final double centerX, final double centerY, final double centerZ, final double normalVectorX, final double normalVectorY, final double normalVectorZ, final Material material) {
        this(new Homogeneous3DVector_Double(centerX, centerY, centerZ, 1), new Homogeneous3DVector_Double(normalVectorX, normalVectorY, normalVectorZ, 0), material);
    }

    /**
     * customized constructor
     * missing values will be set randomly
     *
     * @param center       the plane's center (rotating-point of the plane)
     * @param normalVector the plane's normal. It defines the planes outside. Changing it will rotate the plane around the plane's center.
     */
    public Plane(final Homogeneous3DVector_Double center, final Homogeneous3DVector_Double normalVector) {
        this(center, normalVector, new Material());
    }

    /**
     * customized constructor
     * missing values will be set randomly
     *
     * @param centerX       the plane's center x-value (rotating-point of the plane)
     * @param centerY       the plane's center y-value(rotating-point of the plane)
     * @param centerZ       the plane's center z-value(rotating-point of the plane)
     * @param normalVectorX the plane's normal x-value. The normalVector defines the planes outside. Changing it will rotate the plane around the plane's center.
     * @param normalVectorY the plane's normal y-value. The normalVector defines the planes outside. Changing it will rotate the plane around the plane's center.
     * @param normalVectorZ the plane's normal z-value. The normalVector defines the planes outside. Changing it will rotate the plane around the plane's center.
     */
    public Plane(final double centerX, final double centerY, final double centerZ, final double normalVectorX, final double normalVectorY, final double normalVectorZ) {
        this(new Homogeneous3DVector_Double(centerX, centerY, centerZ, 1), new Homogeneous3DVector_Double(normalVectorX, normalVectorY, normalVectorZ, 0), new Material());
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
    public Homogeneous3DVector_Double calculateNormalAtPosition(Homogeneous3DVector_Double position) {
        return this.normalVector;
    }

    /**
     * the mathematically basics of this method are from Markus Trenkwalders Bakkalaureatsarbeit "Implementierung eines Raytracers"
     */
    @Override
    public Hit intersect(Ray potentialHittingRay) {
        double d = -Homogeneous3DVector_Double_Math.scalarProdukt(this.normalVector, this.center);
        Homogeneous3DVector_Double rayOrigin = potentialHittingRay.getOrigin();
        Homogeneous3DVector_Double rayDirection = potentialHittingRay.getDirection();
        double t = -(Homogeneous3DVector_Double_Math.scalarProdukt(this.normalVector, rayOrigin) + d) / Homogeneous3DVector_Double_Math.scalarProdukt(this.normalVector, rayDirection);
        if (t > 0 && t < Double.MAX_VALUE) {
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
