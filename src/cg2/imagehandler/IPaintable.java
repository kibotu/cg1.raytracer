package cg2.imagehandler;

import cg2.util.RGBAColor;

/**
 * Subclass <code>IPainter</code> to specify how an image should be painted.
 */
public interface IPaintable {

    /**
     * Override to determine the pixel color for one pixel of the image.
     *
     * @param currentPixelX The X-coordinate of the pixel.
     * @param currentPixelY The Y-Coordinate of the pixel.
     * @param imageWidth    The image width in pixels.
     * @param imageHeight   The image height in pixels.
     * @return The specified pixel's color
     */
    public abstract RGBAColor pixelColorAt(int currentPixelX, int currentPixelY, int imageWidth, int imageHeight);
}
