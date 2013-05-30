package cg2.raytracer;

import cg2.math.Homogeneous3DVector_Double;
import cg2.scenegraph.ADefaultSceneObject;

/**
 * @author Albert
 *         <p/>
 *         hit objects represent a the rays hit with an object
 *         a hit hold informations like:
 *         the ray which has hit something
 *         the object which was hit by the ray
 *         the position where the ray has hit the object
 *         the distance factor which can be used to calculate the distance between the object which was hit and the ray origin
 */
public class Hit {

    private final ADefaultSceneObject hitSceneObject;
    private final Ray hittingRay;
    private final double rayDistanceParameter;

    /**
     * the position where the hit had hit an object
     */
    private final Homogeneous3DVector_Double hitPosition;

    /**
     * the hit objects normal at the position it was hit
     */
    private final Homogeneous3DVector_Double hitNormal;

    /**
     * complete constructor
     *
     * @param hitSceneObject       the object which was hit by a ray
     * @param hittingRay           the ray which has hit an object
     * @param rayDistanceParameter
     * @param hitPostion
     * @param hitNormal
     */
    public Hit(ADefaultSceneObject hitSceneObject, Ray hittingRay, double rayDistanceParameter, Homogeneous3DVector_Double hitPostion, Homogeneous3DVector_Double hitNormal) {
        this.hitSceneObject = hitSceneObject;
        this.hittingRay = hittingRay;
        this.rayDistanceParameter = rayDistanceParameter;
        this.hitPosition = hitPostion;
        this.hitNormal = hitNormal;
    }

    public ADefaultSceneObject getHitSceneObject() {
        return hitSceneObject;
    }

    public Ray getHittingRay() {
        return hittingRay;
    }

    public double getRayDistanceParameter() {
        return rayDistanceParameter;
    }

    public Homogeneous3DVector_Double getHitPosition() {
        return hitPosition;
    }

    public Homogeneous3DVector_Double getHitNormal() {
        return hitNormal;
    }
}
