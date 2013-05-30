package cg2.scenegraph.primitives;

import cg2.math.Homogeneous3DVector_Double;
import cg2.math.Homogeneous3DVector_Double_Math;
import cg2.raytracer.Hit;
import cg2.raytracer.Ray;
import cg2.scenegraph.ADefaultSceneObject;
import cg2.util.IMaterial;
import cg2.util.Material;
import cg2.util.RGBAColor;

/**
 * @author Albert
 */
public class Cuboid extends ADefaultSceneObject implements IQuader {

    /**
     * the cuboids center (rotating-point of the plane)
     */
    final Homogeneous3DVector_Double center;

    /**
     * the cuboids width
     */
    final double width;

    /**
     * the cuboids height
     */
    final double height;

    /**
     * the cuboids depth
     */
    final double depth;

    /**
     * the cuboids material
     */
    private final Material material;

    /**
     * a complete constructor
     *
     * @param center   the cuboids center
     * @param width    the cuboids width
     * @param height   the cuboids height
     * @param depth    the cuboids depth
     * @param material the cuboids material
     */
    public Cuboid(final Homogeneous3DVector_Double center, final double width, final double height, final double depth, final Material material) {
        this.center = center;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.material = material;
    }

    /**
     * a complete constructor
     *
     * @param centerX  the cuboids center x position
     * @param centerY  the cuboids center y position
     * @param centerZ  the cuboids center z position
     * @param width    the cuboids width
     * @param height   the cuboids height
     * @param depth    the cuboids depth
     * @param material the cuboids material
     */
    public Cuboid(final double centerX, final double centerY, final double centerZ, final double width, final double height, final double depth, final Material material) {
        this(new Homogeneous3DVector_Double(centerX, centerY, centerZ, 1), width, height, depth, material);
    }

    /**
     * a customized constructor
     *
     * @param center the cuboids center
     * @param width  the cuboids width
     * @param height the cuboids height
     * @param depth  the cuboids depth
     */
    public Cuboid(final Homogeneous3DVector_Double center, final double width, final double height, final double depth) {
        this(center, width, height, depth, new Material());
    }

    /**
     * a customized constructor
     *
     * @param centerX the cuboids center x position
     * @param centerY the cuboids center y position
     * @param centerZ the cuboids center z position
     * @param width   the cuboids width
     * @param height  the cuboids height
     * @param depth   the cuboids depth
     */
    public Cuboid(final double centerX, final double centerY, final double centerZ, final double width, final double height, final double depth) {
        this(new Homogeneous3DVector_Double(centerX, centerY, centerZ, 1), width, height, depth, new Material());
    }

    @Override
    public RGBAColor getColor() {
        return material.getMaterialColor();
    }

    @Override
    public IMaterial getMaterial() {
        return this.material;
    }

    @Override
    public Homogeneous3DVector_Double calculateNormalAtPosition(Homogeneous3DVector_Double position) {
        double[][] values = position.getMatrixValues();
        double[][] centerValues = center.getMatrixValues();
        double delta = 0.000000000000001;
        if (values[0][0] >= centerValues[0][0] - width / 2 - delta && values[0][0] <= centerValues[0][0] - width / 2 + delta) {
            return new Homogeneous3DVector_Double(-1, 0, 0, 0);
        }
        if (values[0][0] >= centerValues[0][0] + width / 2 - delta && values[0][0] <= centerValues[0][0] + width / 2 + delta) {
            return new Homogeneous3DVector_Double(1, 0, 0, 0);
        }
        if (values[1][0] >= centerValues[1][0] - height / 2 - delta && values[1][0] <= centerValues[1][0] - height / 2 + delta) {
            return new Homogeneous3DVector_Double(0, -1, 0, 0);
        }
        if (values[1][0] >= centerValues[1][0] + height / 2 - delta && values[1][0] <= centerValues[1][0] + height / 2 + delta) {
            return new Homogeneous3DVector_Double(0, 1, 0, 0);
        }
        if (values[2][0] >= centerValues[2][0] - depth / 2 - delta && values[2][0] <= centerValues[2][0] - depth / 2 + delta) {
            return new Homogeneous3DVector_Double(0, 0, -1, 0);
        }
        if (values[2][0] >= centerValues[2][0] + depth / 2 - delta && values[2][0] <= centerValues[2][0] + depth / 2 + delta) {
            return new Homogeneous3DVector_Double(0, 0, 1, 0);
        }
        return Homogeneous3DVector_Double_Math.subtract(position, this.center).normalize();
    }

    /**
     * the mathematically basics of this method are from Markus Trenkwalders Bakkalaureatsarbeit "Implementierung eines Raytracers"
     */
    @Override
    public Hit intersect(Ray potentialHittingRay) {

        Homogeneous3DVector_Double rayOrigin = potentialHittingRay.getOrigin();
        Homogeneous3DVector_Double rayDirection = potentialHittingRay.getDirection();

        final double tLeft = -(-1 * rayOrigin.getX() + -(-1 * (this.center.getX() - 0.5 * width))) / (-1 * rayDirection.getX());
        final double tRight = -(1 * rayOrigin.getX() + -(1 * (this.center.getX() + 0.5 * width))) / (1 * rayDirection.getX());
        final double tBottom = -(-1 * rayOrigin.getY() + -(-1 * (this.center.getY() - 0.5 * height))) / (-1 * rayDirection.getY());
        final double tTop = -(1 * rayOrigin.getY() + -(1 * (this.center.getY() + 0.5 * height))) / (1 * rayDirection.getY());
        final double tFront = -(1 * rayOrigin.getZ() + -(1 * (this.center.getZ() + 0.5 * depth))) / (1 * rayDirection.getZ());
        final double tBack = -(-1 * rayOrigin.getZ() + -(-1 * (this.center.getZ() - 0.5 * depth))) / (-1 * rayDirection.getZ());

        final double tUMin = Math.min(tLeft, tRight);
        final double tUMax = Math.max(tLeft, tRight);
        final double tVMin = Math.min(tTop, tBottom);
        final double tVMax = Math.max(tTop, tBottom);
        final double tWMin = Math.min(tFront, tBack);
        final double tWMax = Math.max(tFront, tBack);

        final double tMin = Math.max(Math.max(tUMin, tVMin), tWMin);
        final double tMax = Math.min(Math.min(tUMax, tVMax), tWMax);

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
        return null;
    }

}
