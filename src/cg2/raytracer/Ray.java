package cg2.raytracer;

import cg2.math.Homogeneous3DVector_Double;
import cg2.scenegraph.ADefaultSceneObject;

/**
 * @author Albert
 *         <p/>
 *         Instances of this class represent rays with origin and direction.
 *         This rays can hit objects in a specified scene.
 */
public class Ray {

    /**
     * the rays origin
     */
    private final Homogeneous3DVector_Double origin;

    /**
     * the rays direction
     */
    private final Homogeneous3DVector_Double direction;


    /**
     * complete constructor
     *
     * @param origin    the rays origin
     * @param direction the rays direction
     */
    public Ray(Homogeneous3DVector_Double origin, Homogeneous3DVector_Double direction) {
        this.origin = origin;
        this.direction = direction.normalize();
    }

    /**
     * returns the rays origin
     *
     * @return the rays origin
     */
    public Homogeneous3DVector_Double getOrigin() {
        return origin;
    }

    /**
     * returns the rays direction
     *
     * @return returns the rays direction
     */
    public Homogeneous3DVector_Double getDirection() {
        return direction;
    }

    /**
     * returns the closest hit in the rays direction starting by the rays origin
     *
     * @param sceneObjectToSearchThroughFrom the object which an which children will be tested if they are hit by the ray
     * @return a hit with the intersected object which has the smallest distance to the rays origin
     */
    public Hit findClosestHit(ADefaultSceneObject sceneObjectToSearchThroughFrom) {
        Hit currentClosestHit = null;
        return this.findClosestHit(sceneObjectToSearchThroughFrom, currentClosestHit);
    }

    /**
     * returns the closest hit in the rays direction starting by the rays origin
     *
     * @param sceneObjectToSearchThroughFrom the object which an which children will be tested if they will be hit by the ray
     * @param currentClosestHit              the currently closest hit
     * @return the intersected object which has the smallest distance to the rays origin
     */
    private Hit findClosestHit(ADefaultSceneObject potentialRaytraceable, Hit currentClosestHit) {

        //Just IRaytraceable can be hitted by a ray
        if (potentialRaytraceable instanceof IRaytraceable) {
            //The SceneObject is a IRaytraceable
            IRaytraceable raytraceable = (IRaytraceable) potentialRaytraceable;
            //getting the hit object the IRaytraceable object returns
            Hit potentialClosestHit = raytraceable.intersect(this);
            //checking if the potentialClosestHit is a valid Hit
            if (potentialClosestHit != null && potentialClosestHit.getRayDistanceParameter() >= 0.0000000000000001) {
                //checking if the potentialClosestHit is closer then the currentClosestHit
                if (currentClosestHit == null || potentialClosestHit.getRayDistanceParameter() < currentClosestHit.getRayDistanceParameter()) {
                    currentClosestHit = potentialClosestHit;
                }
            }
        }

        //recursive Part. traversing through all children
        for (ADefaultSceneObject sceneObject : potentialRaytraceable.getChildren()) {
            currentClosestHit = findClosestHit(sceneObject, currentClosestHit);
        }

        return currentClosestHit;
    }

}
