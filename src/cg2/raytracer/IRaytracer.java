package cg2.raytracer;

import cg2.imagehandler.IPaintable;

/**
 * @author Albert
 *         <p/>
 *         implementations can generate rays and determine there closest hit
 */
public interface IRaytracer extends IPaintable {

    /**
     * Override to determine the pixel color for one pixel of the image.
     *
     * @param currentPixelX The X-coordinate of the pixel.
     * @param currentPixelY The Y-Coordinate of the pixel.
     * @param imageWidth    The image width in pixels.
     * @param imageHeight   The image height in pixels.
     * @return the ray for the for the specified values
     */
    public abstract Ray generateRay(int currentPixelX, int currentPixelY, int imageWidth, int imageHeight);

    public abstract Hit findClosestHit(Ray potentialHittingRay);
}
