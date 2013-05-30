package cg2.scenegraph.primitives;

import cg2.math.Homogeneous3DVector_Double;
import cg2.math.Homogeneous3DVector_Double_Math;
import cg2.raytracer.Hit;
import cg2.raytracer.Ray;
import cg2.scenegraph.ADefaultSceneObject;
import cg2.util.IMaterial;
import cg2.util.Material;
import cg2.util.RGBAColor;

public class Sphere extends ADefaultSceneObject implements ISphere {

    /**
     * the spheres center
     */
    private final Homogeneous3DVector_Double center;

    /**
     * the spheres radius
     */
    private final double radius;

    /**
     * the spheres material
     */
    private final Material material;

    /**
     * complete constructor
     *
     * @param center   the spheres center
     * @param radius   the spheres radius
     * @param material the spheres material
     */
    public Sphere(final Homogeneous3DVector_Double center, final double radius, final Material material) {
        super();
        this.center = center;
        this.radius = Math.abs(radius);
        this.material = material;
    }

    /**
     * complete constructor
     *
     * @param centerX  the spheres center x-value
     * @param centerY  the spheres center y-value
     * @param centerZ  the spheres center z-value
     * @param radius   the spheres radius
     * @param material the spheres material
     */
    public Sphere(final double centerX, final double centerY, final double centerZ, final double radius, final Material material) {
        this(new Homogeneous3DVector_Double(centerX, centerY, centerZ, 1), radius, material);
    }

    /**
     * customized constructor
     * missing values will be set randomly
     *
     * @param center the spheres center
     * @param radius the spheres radius
     */
    public Sphere(final Homogeneous3DVector_Double center, final double radius) {
        this(center, radius, new Material());
    }

    /**
     * complete constructor
     * missing values will be set randomly
     *
     * @param centerX the spheres center x-value
     * @param centerY the spheres center y-value
     * @param centerZ the spheres center z-value
     * @param radius  the spheres radius
     */
    public Sphere(final double centerX, final double centerY, final double centerZ, final double radius) {
        this(new Homogeneous3DVector_Double(centerX, centerY, centerZ, 1), radius, new Material());
    }

    @Override
    public RGBAColor getColor() {
        return this.material.getMaterialColor();
    }

    @Override
    public IMaterial getMaterial() {
        return this.material;
    }

    /**
     * calculates the normal of this scene object at the specified position
     * just delivers usable values if the specified position lies on this scene object
     */
    public Homogeneous3DVector_Double calculateNormalAtPosition(final Homogeneous3DVector_Double position) {
        return Homogeneous3DVector_Double_Math.subtract(position, this.center).normalize();
    }

    /**
     * the mathematically basics of this method are from Markus Trenkwalders Bakkalaureatsarbeit "Implementierung eines Raytracers"
     */
    @Override
    public Hit intersect(Ray potentialHittingRay) {
        //vector from ray origin to the sphere center
        final Homogeneous3DVector_Double l = Homogeneous3DVector_Double_Math.subtract(this.center, potentialHittingRay.getOrigin());
        // l^2 = the quadratic length of l
        final double l2 = Homogeneous3DVector_Double_Math.scalarProdukt(l, l);
        //projection from l to the ray direction
        final double s = Homogeneous3DVector_Double_Math.scalarProdukt(l, potentialHittingRay.getDirection());
        //the quadratic radius of the sphere
        final double r2 = radius * radius;
        if (s >= 0 || l2 >= r2) {
            //the quadratic shortest distance between sphere-center and the ray
            final double m2 = l2 - s * s;
            if (m2 <= r2) {
                final double q = Math.sqrt(r2 - m2);
                final double tMin = Math.min(s + q, s - q);
                final double tMax = Math.max(s + q, s - q);
                if (tMin <= tMax && tMax >= 0) {
                    if (tMin >= 0) {
                        Homogeneous3DVector_Double hitPosition = Homogeneous3DVector_Double_Math.add(potentialHittingRay.getOrigin(), Homogeneous3DVector_Double_Math.scalarMultiplicate(tMin, potentialHittingRay.getDirection()));
                        Homogeneous3DVector_Double hitNormal = calculateNormalAtPosition(hitPosition);
                        return new Hit(this, potentialHittingRay, tMin, hitPosition, hitNormal);
                    }
                    Homogeneous3DVector_Double hitPosition = Homogeneous3DVector_Double_Math.add(potentialHittingRay.getOrigin(), Homogeneous3DVector_Double_Math.scalarMultiplicate(tMax, potentialHittingRay.getDirection()));
                    Homogeneous3DVector_Double hitNormal = calculateNormalAtPosition(hitPosition);
                    return new Hit(this, potentialHittingRay, tMax, hitPosition, hitNormal);
                }
            }
        }
        return null;
    }

}
