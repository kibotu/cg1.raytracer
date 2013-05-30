package cg2.imagehandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Generate an image to specification and save it to disk in PNG format.
 */
public class ImageHandler extends ABufferedImageGenerator {

    /**
     * complete constructor
     *
     * @param paintable   the object which delivers the color informations for the picture
     * @param imageWidth  Image width in pixel
     * @param imageHeight Image height in pixel
     */
    public ImageHandler(final IPaintable paintable, final int imageWidth, final int imageHeight) {
        super(paintable, imageWidth, imageHeight);
    }

    /**
     * customized constructor
     * instantiate a generator for default sized images with width = 480 and height = 320
     *
     * @param paintable the object which delivers the color informations for the picture
     */
    public ImageHandler(final IPaintable paintable) {
        super(paintable);
    }

    /**
     * saves the given bufferedImage in a file at the specified file-path in the specified image-format
     *
     * @param bufferedImage the buffered image that shall be saved
     * @param filePath      the path where the image shall be saved
     * @param imageFormat   the image-format in which the image shall be saved
     * @throws IOException
     */
    public static void saveImage(BufferedImage bufferedImage, String filePath, String imageFormat) throws IOException {
        File file = new File(filePath);
        ImageIO.write(bufferedImage, imageFormat, file);
    }

    /**
     * Opens the specified image in the systems default viewer
     *
     * @param filePath the images file path
     * @throws IOException
     */
    public static void openImage(String filePath) throws IOException {
        File file = new File(filePath);
        Desktop.getDesktop().open(file);
    }

}

