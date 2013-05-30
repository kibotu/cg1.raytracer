package cg2.scenegraph.lights;

import cg2.math.Homogeneous3DVector_Double;
import cg2.scenegraph.ADefaultSceneObject;

/**
 * @author Albert
 *         <p/>
 *         a simple light with a position and a light-strength for red, green and blue
 */
public class DotLight extends ADefaultSceneObject implements ILight {

    /**
     * the position-vector which represents the lights position
     */
    private final Homogeneous3DVector_Double positionVector;

    /**
     * the strength of the lights red
     */
    private double lightFactorRed = 0.5;

    /**
     * the strength of the lights green
     */
    private double lightFactorGreen = 0.5;

    /**
     * the strength of the lights blue
     */
    private double lightFactorBlue = 0.5;

    /**
     * a complete constructor
     *
     * @param positionVector the lights position
     */
    public DotLight(final Homogeneous3DVector_Double positionVector) {
        this.positionVector = positionVector;
    }

    /**
     * a complete constructor
     *
     * @param x the lights position x value
     * @param y the lights position y value
     * @param z the ligths position z value
     */
    public DotLight(final double x, final double y, final double z) {
        this.positionVector = new Homogeneous3DVector_Double(x, y, z, 1);
    }

    /**
     * returns the lights position vector
     *
     * @return the lights position vector
     */
    public Homogeneous3DVector_Double getPositionVector() {
        return new Homogeneous3DVector_Double(positionVector);
    }

    /**
     * returns the red lights strength
     *
     * @return the red lights strength
     */
    public double getLightFactorRed() {
        return lightFactorRed;
    }

    /**
     * returns the green lights strength
     *
     * @return the green lights strength
     */
    public double getLightFactorGreen() {
        return lightFactorGreen;
    }

    /**
     * returns the blue lights strength
     *
     * @return the blue lights strength
     */
    public double getLightFactorBlue() {
        return lightFactorBlue;
    }

    /**
     * sets the red lights strength
     *
     * @param lightFactorRed the red lights strength
     */
    public void setLightFactorRed(double lightFactorRed) {
        this.lightFactorRed = lightFactorRed;
    }

    /**
     * sets the green lights strength
     *
     * @param lightFactorGreen the green lights strength
     */
    public void setLightFactorGreen(double lightFactorGreen) {
        this.lightFactorGreen = lightFactorGreen;
    }

    /**
     * sets the blue lights strength
     *
     * @param lightFactorBlue the blue lights strength
     */
    public void setLightFactorBlue(double lightFactorBlue) {
        this.lightFactorBlue = lightFactorBlue;
    }
}
