package cg2.scenegraph.lights;

import cg2.math.Homogeneous3DVector_Double;
import cg2.math.Homogeneous3DVector_Double_Math;
import cg2.scenegraph.ADefaultSceneObject;
import cg2.scenegraph.primitives.Sphere;
import cg2.util.Config;

/**
 * @author Albert
 *         <p/>
 *         area-lights are oriented rectangles build of a grid of dot-lights
 */
public class AreaLight extends ADefaultSceneObject {

    /**
     * a help vector to calculate the area-lights orientation
     */
    private final Homogeneous3DVector_Double upVector = new Homogeneous3DVector_Double(0, 1, 0, 0).normalize();

    /**
     * a help vector to calculate the area-lights orientation
     */
    private final Homogeneous3DVector_Double rightVector = new Homogeneous3DVector_Double(1, 0, 0, 0).normalize();

    /**
     * a complete constructor
     *
     * @param positionVector   the area-lights center position
     * @param lightDirection   the area-lights orientation
     * @param lightFactorRed   the red light factor specifies the strength of the red light
     *                         have to be over 0
     * @param lightFactorGreen the green light factor specifies the strength of the green light
     *                         have to be over 0
     * @param lightFactorBlue  the blue light factor specifies the strength of the blue light
     *                         have to be over 0
     * @param height           the area-lights area height
     * @param width            the area-lights area width
     * @param numberOfRows     the number of light-rows
     * @param numberOfColumns  the number of light-columns
     */
    public AreaLight(final Homogeneous3DVector_Double positionVector, Homogeneous3DVector_Double lightDirection, final double lightFactorRed, final double lightFactorGreen, final double lightFactorBlue, final double height, final double width, final int numberOfRows, final int numberOfColumns) {
        lightDirection = lightDirection.normalize();

        Homogeneous3DVector_Double xDirection;
        Homogeneous3DVector_Double yDirection;

        if (lightDirection.getValueAt(0, 0) == 0 && Math.abs(lightDirection.getValueAt(1, 0)) == 1 && lightDirection.getValueAt(2, 0) == 0) {
            xDirection = Homogeneous3DVector_Double_Math.crossProduct(lightDirection, rightVector);
            yDirection = Homogeneous3DVector_Double_Math.crossProduct(lightDirection, xDirection);
        } else {
            xDirection = Homogeneous3DVector_Double_Math.crossProduct(lightDirection, upVector);
            yDirection = Homogeneous3DVector_Double_Math.crossProduct(lightDirection, xDirection);
        }
        final Homogeneous3DVector_Double topLeftPostion = Homogeneous3DVector_Double_Math.subtract(Homogeneous3DVector_Double_Math.subtract(positionVector, Homogeneous3DVector_Double_Math.scalarMultiplicate(width / 2, xDirection)), Homogeneous3DVector_Double_Math.scalarMultiplicate(height / 2, yDirection));
        final double xDistanceBetweenLights = (double) width / (double) (numberOfColumns - 1);
        final double yDistanceBetweenLights = (double) height / (double) (numberOfRows - 1);

        for (int c = 0; c < numberOfColumns; c++) {
            for (int r = 0; r < numberOfRows; r++) {
                if (Config.depugModeOn) {
                    this.addChild(new Sphere(Homogeneous3DVector_Double_Math.add(Homogeneous3DVector_Double_Math.add(topLeftPostion, Homogeneous3DVector_Double_Math.scalarMultiplicate(c * xDistanceBetweenLights, xDirection)), Homogeneous3DVector_Double_Math.scalarMultiplicate(r * yDistanceBetweenLights, yDirection)), 0.05));
                } else {
                    DotLight dotLight = new DotLight(Homogeneous3DVector_Double_Math.add(Homogeneous3DVector_Double_Math.add(topLeftPostion, Homogeneous3DVector_Double_Math.scalarMultiplicate(c * xDistanceBetweenLights, xDirection)), Homogeneous3DVector_Double_Math.scalarMultiplicate(r * yDistanceBetweenLights, yDirection)));
                    int numberOfLights = numberOfRows * numberOfColumns;
                    dotLight.setLightFactorRed(lightFactorRed / numberOfLights);
                    dotLight.setLightFactorGreen(lightFactorGreen / numberOfLights);
                    dotLight.setLightFactorBlue(lightFactorBlue / numberOfLights);
                    this.addChild(dotLight);
                }
            }
        }
    }

    /**
     * a complete constructor
     *
     * @param positionX        the area-lights center position x value
     * @param positionY        the area-lights center position y value
     * @param positionZ        the area-lights center position z value
     * @param lightDirectionX  the area-lights orientation x value
     * @param lightDirectionY  the area-lights orientation y value
     * @param lightDirectionZ  the area-lights orientation z value
     * @param lightFactorRed   the red light factor specifies the strength of the red light
     *                         have to be over 0
     * @param lightFactorGreen the green light factor specifies the strength of the green light
     *                         have to be over 0
     * @param lightFactorBlue  the blue light factor specifies the strength of the blue light
     *                         have to be over 0
     * @param height           the area-lights area height
     * @param width            the area-lights area width
     * @param numberOfRows     the number of light-rows
     * @param numberOfColumns  the number of light-columns
     */
    public AreaLight(final double positionX, final double positonY, final double positonZ, final double lightDirectionX, final double lightDirectionY, final double lightDirectionZ, final double lightFactorRed, final double lightFactorGreen, final double lightFactorBlue, final double height, final double width, final int numberOfRows, final int numberOfColumns) {
        this(new Homogeneous3DVector_Double(positionX, positonY, positonZ, 1), new Homogeneous3DVector_Double(lightDirectionX, lightDirectionY, lightDirectionZ, 0), lightFactorRed, lightFactorGreen, lightFactorBlue, height, width, numberOfRows, numberOfColumns);
    }

}
