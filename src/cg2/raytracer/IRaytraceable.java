package cg2.raytracer;

import cg2.scenegraph.itemabilities.IHasColor;


/**
 * @author Albert
 *         <p/>
 *         the objects of classes which implements this interface can be hit by a ray
 */
public interface IRaytraceable extends IHasColor {

    /**
     * tests if the specified ray hits an objects and returns a hit object
     *
     * @param potentialHittingRay the ray which potentially hit an object
     * @return a hit object for the specified ray and the object which was hit
     */
    public abstract Hit intersect(Ray potentialHittingRay);

}
