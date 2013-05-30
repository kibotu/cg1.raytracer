package cg2.scenegraph.lights;

import cg2.scenegraph.ADefaultSceneObject;

/**
 * @author Albert
 *         <p/>
 *         a light without position
 */
public class AmbientLight extends ADefaultSceneObject implements ILight {

    /**
     * the strength of the lights red
     */
    private double ambientLightFactorRed;

    /**
     * the strength of the lights green
     */
    private double ambientLightFactorGreen;

    /**
     * the strength of the lights blue
     */
    private double ambientLightFactorBlue;

    /**
     * a complete constructor
     *
     * @param ambientLightFactorRed   the red lights strength
     * @param ambientLightFactorGreen the green lights strength
     * @param ambientLightFactorBlue  the blue lights strength
     */
    public AmbientLight(double ambientLightFactorRed, double ambientLightFactorGreen, double ambientLightFactorBlue) {
        this.ambientLightFactorRed = ambientLightFactorRed;
        this.ambientLightFactorGreen = ambientLightFactorGreen;
        this.ambientLightFactorBlue = ambientLightFactorBlue;
    }

    /**
     * returns the red lights strength
     *
     * @return the red lights strength
     */
    public double getAmbientLightFactorRed() {
        return ambientLightFactorRed;
    }

    /**
     * returns the green lights strength
     *
     * @return the green lights strength
     */
    public double getAmbientLightFactorGreen() {
        return ambientLightFactorGreen;
    }

    /**
     * returns the blue lights strength
     *
     * @return the blue lights strength
     */
    public double getAmbientLightFactorBlue() {
        return ambientLightFactorBlue;
    }

    /**
     * sets the red lights strength
     *
     * @param ambientLightFactorRed the red lights strength
     */
    public void setAmbientLightFactorRed(double ambientLightFactorRed) {
        this.ambientLightFactorRed = ambientLightFactorRed;
    }

    /**
     * sets the green lights strength
     *
     * @param ambientLightFactorGreen the green lights strength
     */
    public void setAmbientLightFactorGreen(double ambientLightFactorGreen) {
        this.ambientLightFactorGreen = ambientLightFactorGreen;
    }

    /**
     * sets the blue lights strength
     *
     * @param ambientLightFactorBlue the blue lights strength
     */
    public void setAmbientLightFactorBlue(double ambientLightFactorBlue) {
        this.ambientLightFactorBlue = ambientLightFactorBlue;
    }
}
