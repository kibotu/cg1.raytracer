package cg2.imagehandler;

import cg2.util.RGBAColor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Albert
 *         <p/>
 *         instances can generate buffered images
 */
abstract class ABufferedImageGenerator {

    /**
     * the image width in pixel
     * every value above 0 is valid
     */
    final private int imageWidth;

    /**
     * the image height in pixel
     * every value above 0 is valid
     */
    final private int imageHeight;

    /**
     * the object which delivers the color informations for the picture
     */
    final private IPaintable paintable;

    /**
     * complete constructor
     *
     * @param paintable   the object which delivers the color informations for the picture
     * @param imageWidth  Image width in pixel
     * @param imageHeight Image height in pixel
     */
    public ABufferedImageGenerator(final IPaintable paintable, final int imageWidth, final int imageHeight) {
        this.paintable = paintable;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    /**
     * customized constructor
     * instantiate a generator for default sized images with width = 480 and height = 320
     *
     * @param paintable the object which delivers the color informations for the picture
     */
    public ABufferedImageGenerator(final IPaintable paintable) {
        this(paintable, 480, 320);
    }

    /**
     * generates a buffered image with the color-informations it gets from the paintable object
     *
     * @return the generated buffered image
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public BufferedImage generateBufferedImage() throws InterruptedException, ExecutionException {

        //creates a threadpool with a number of threads matching the number of currently available processors - 1
        ExecutorService executor = Executors.newFixedThreadPool(Math.max(Runtime.getRuntime().availableProcessors() - 1, 1));

        // create a buffered image object using ARGB pixel format
        BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);

        Collection<Callable<PixelColorGetter>> tasks = new ArrayList<Callable<PixelColorGetter>>();

        // Set a color for each pixel. Y pixel indices are flipped so that the origin (0,0) is at the bottom left of the image.
        for (int i = 0; i < imageWidth; i++) {
            for (int j = 0; j < imageHeight; j++) {
                tasks.add(new PixelColorGetter(i, j));
            }
        }

        List<Future<PixelColorGetter>> futureResults = executor.invokeAll(tasks);

        for (Future<PixelColorGetter> futureResult : futureResults) {
            bufferedImage.setRGB(futureResult.get().x, futureResult.get().y, futureResult.get().result.asARGBInt());
        }

        executor.shutdown();

        return bufferedImage;
    }

    /**
     * instances of this class can run in a there own thread and deliver the color of a pixel at a specified position
     */
    class PixelColorGetter implements Callable<PixelColorGetter> {

        /**
         * the x value of the position of the pixel which color have to be determined
         */
        private final int x;

        /**
         * the y value of the position of the pixel which color have to be determined
         */
        private final int y;

        /**
         * the determined color at the specified position
         */
        private RGBAColor result;

        /**
         * complete constructor
         *
         * @param x the x value of the position of the pixel which color have to be determined
         * @param y the y value of the position of the pixel which color have to be determined
         */
        PixelColorGetter(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * returns the x value of the position of the pixel which color have to be determined
         *
         * @return
         */
        public int getX() {
            return x;
        }

        /**
         * returns the y value of the position of the pixel which color have to be determined
         *
         * @return the y value of the position of the pixel which color have to be determined
         */
        public int getY() {
            return y;
        }

        /**
         * if the color of the specified pixel is determined this method will return the result color otherwise a exception will be thrown
         *
         * @return the determined color of the specified pixel
         */
        public RGBAColor getResult() {
            if (result == null) {
                throw (new IllegalStateException("The result isn't dermined yet."));
            }
            return result;
        }

        @Override
        public PixelColorGetter call() throws Exception {
            result = paintable.pixelColorAt(x, imageHeight - y - 1, imageWidth, imageHeight);
            return this;
        }
    }
}
