package cg2.util;


public class RGBAColor {

    /**
     * some default color-definitions
     */
    public static final int RED = 0xFF0000FF;
    public static final int GREEN = 0x00FF00FF;
    public static final int BLUE = 0x0000FFFF;
    public static final int YELLOW = 0xFFFF00FF;
    public static final int PURPLE = 0xFF00FFFF;
    public static final int CYAN = 0x00FFFFFF;
    public static final int BLACK = 0x000000FF;
    public static final int WHITE = 0xFFFFFFFF;
    public static final int LIGHT_GRAY = 0xBBBBBBFF;
    public static final int GRAY = 0x888888FF;
    public static final int DARK_GRAY = 0x444444FF;
    public static final int TRANSPARENT = 0x00000000;

    /**
     * the red float value between inclusive 0 and 1
     */
    private float red;

    /**
     * the green float value between inclusive 0 and 1
     */
    private float green;

    /**
     * the blue float value between inclusive 0 and 1
     */
    private float blue;

    /**
     * the alpha float value between inclusive 0 and 1
     */
    private float alpha;

    /**
     * @param red   the color's red value
     * @param green the color's green value
     * @param blue  the color's blue value
     * @param alpha the color's alpha value
     */
    public RGBAColor(final float red, final float green, final float blue, final float alpha) {
        if (!areValidColorValues0To1(red, green, blue, alpha)) {
            throw (new IllegalArgumentException("One of the values is no valid color-value. It have to be between inclusive " + 0 + " and " + 1 + "."));
        }
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    /**
     * instantiates a new rgbaColor by converting the the specified values, which can be between inclusive 0 and 255, to values between inclusive 0 and 1.
     *
     * @param red   the color's red value
     * @param green the color's green value
     * @param blue  the color's blue value
     * @param alpha the color's alpha value
     */
    public RGBAColor(final int red, final int green, final int blue, final int alpha) {
        if (!areValidColorValues0To255(red, green, blue, alpha)) {
            throw (new IllegalArgumentException("One of the values is no valid color-value. It have to be between inclusive " + 0 + " and " + 255 + "."));
        }
        this.red = red / 255f;
        this.green = green / 255f;
        this.blue = blue / 255f;
        this.alpha = alpha / 255f;
    }

    /**
     * @param red   the color's red value
     * @param green the color's green value
     * @param blue  the color's blue value
     */
    public RGBAColor(final float red, final float green, final float blue) {
        this(red, green, blue, 1);
    }

    /**
     * instantiates a new rgbaColor by converting the the specified values, which can be between inclusive 0 and 255, to values between inclusive 0 and 1.
     *
     * @param red   the color's red value
     * @param green the color's green value
     * @param blue  the color's blue value
     */
    public RGBAColor(final int red, final int green, final int blue) {
        this(red, green, blue, 255);
    }

    /**
     * instantiates a new rgbaColor by extracting the rgba-values out of the specified rgba-int.
     * The rgba-int-values first 8 bit from the right side are for alpha
     * the next 8 for blue the next 8 for green and the last 8 for red
     *
     * @param rgba        the int-value which contains the rgba-values
     * @param ignoreAlpha specifies if the alpha channel have to be ignored. true = no alpha
     */
    public RGBAColor(final int rgba, final boolean ignoreAlpha) {
        this.red = (rgba >> 24 & 0x000000FF) / 255f;
        this.green = (rgba >> 16 & 0x000000FF) / 255f;
        this.blue = (rgba >> 8 & 0x000000FF) / 255f;
        if (ignoreAlpha) {
            this.alpha = 1;
        } else {
            this.alpha = (rgba >> 0 & 0x000000FF) / 255f;
        }
    }

    /**
     * instantiates a new rgbaColor by extracting the rgba-values out of the specified rgba-int.
     * The rgba-int-values first 8 bit from the right side are for alpha
     * the next 8 for blue the next 8 for green and the last 8 for red
     *
     * @param rgba the int-value which contains the rgba-values
     */
    public RGBAColor(final int rgba) {
        this(rgba, false);
    }

    /**
     * instantiates a new random rgbaColor
     *
     * @param ignoreAlpha specifies if the alpha channel have to be ignored. true = no alpha
     */
    public RGBAColor(final boolean ignoreAlpha) {
        this((int) (255 * Math.random()) << 24 | (int) (255 * Math.random()) << 16 | (int) (255 * Math.random()) << 8, ignoreAlpha);
    }

    /**
     * instantiates a new random rgbaColor
     */
    public RGBAColor() {
        this((float) Math.random(), (float) Math.random(), (float) Math.random(), (float) Math.random());
    }

    /**
     * returns the colors red value
     * Range 0 - 1
     *
     * @return the colors red value
     */
    public float getRed() {
        return red;
    }

    /**
     * returns the colors green value
     * Range 0 - 1
     *
     * @return the colors green value
     */
    public float getGreen() {
        return green;
    }

    /**
     * returns the colors blue value
     * Range 0 - 1
     *
     * @return the colors blue value
     */
    public float getBlue() {
        return blue;
    }

    /**
     * returns the colors alpha value
     * Range 0 - 1
     *
     * @return the colors alpha value
     */
    public float getAlpha() {
        return alpha;
    }

    /**
     * sets the colors red value
     * Range 0 - 1
     *
     * @param newRed the colors new red value
     */
    protected void setRed(final float newRed) {
        if (!areValidColorValues0To1(newRed)) {
            throw (new IllegalArgumentException(newRed + " is no valid color value. It have to be between 0 and 1."));
        }
        this.red = newRed;
    }

    /**
     * sets the colors green value
     * Range 0 - 1
     *
     * @param newGreen the colors new green value
     */
    protected void setGreen(final float newGreen) {
        if (!areValidColorValues0To1(newGreen)) {
            throw (new IllegalArgumentException(newGreen + " is no valid color value. It have to be between 0 and 1."));
        }
        this.red = newGreen;
    }

    /**
     * sets the colors blue value
     * Range 0 - 1
     *
     * @param newBlue the colors new blue value
     */
    protected void setBlue(final float newBlue) {
        if (!areValidColorValues0To1(newBlue)) {
            throw (new IllegalArgumentException(newBlue + " is no valid color value. It have to be between 0 and 1."));
        }
        this.red = newBlue;
    }

    /**
     * sets the colors alpha value
     * Range 0 - 1
     *
     * @param newAlpha the colors new alpha value
     */
    protected void setAlpha(final float newAlpha) {
        if (!areValidColorValues0To1(newAlpha)) {
            throw (new IllegalArgumentException(newAlpha + " is no valid color value. It have to be between 0 and 1."));
        }
        this.red = newAlpha;
    }

    /**
     * this method returns true if the specified potenzialColorValue is between inclusive the specified min- and max-value
     *
     * @param potenzialColorValue the value to be tested
     * @param minValue            the minimal possible value
     * @param maxValue            the maximal possible value
     * @return true if the potentialColorValue is in the specified range, false if not.
     */
    private boolean areValidColorValues(final float minValue, final float maxValue, final float[] potenzialColorValues) {
        for (float potenzialColorValue : potenzialColorValues) {
            if (potenzialColorValue < minValue || potenzialColorValue > maxValue) {
                return false;
            }
        }
        return true;
    }

    /**
     * this method returns true if the specified potenzialColorValue is between inclusive 0 and 255
     *
     * @param potenzialColorValue the value to be tested
     * @return true if the potentialColorValue is between inclusive 0 and 255, false if not.
     */
    private boolean areValidColorValues0To1(final float... potenzialColorValues) {
        return areValidColorValues(0, 1, potenzialColorValues);
    }

    /**
     * this method returns true if the specified potenzialColorValue is between inclusive 0 and 255
     *
     * @param potenzialColorValue the value to be tested
     * @return true if the potentialColorValue is between inclusive 0 and 255, false if not.
     */
    private boolean areValidColorValues0To255(final float... potenzialColorValues) {
        return areValidColorValues(0, 255, potenzialColorValues);
    }

    /**
     * returns the color as ARGB-int value
     *
     * @return the color as ARGB-int value
     */
    public int asARGBInt() {
        return ((int) (alpha * 255f) << 24 | (int) (red * 255f) << 16 | (int) (green * 255f) << 8 | (int) (blue * 255f) << 0);
    }

    /**
     * returns the color as RGBA-int value
     *
     * @return the color as RGBA-int value
     */
    public int asRGBAInt() {
        return ((int) (red * 255f) << 24 | (int) (green * 255f) << 16 | (int) (blue * 255f) << 8 | (int) (alpha * 255f) << 0);
    }

    @Override
    public String toString() {
        return "Color(red: " + red + ", green: " + green + ", blue: " + blue + ", alpha: " + alpha + ")";
    }
}
