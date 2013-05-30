package cg2.scenegraph.cameras;


import cg2.math.Homogeneous3DVector_Double;
import cg2.raytracer.Hit;
import cg2.raytracer.IRaytraceable;
import cg2.raytracer.Ray;
import cg2.scenegraph.ADefaultSceneObject;
import cg2.scenegraph.itemabilities.IHasColor;
import cg2.scenegraph.itemabilities.IShadeable;
import cg2.util.Config;
import cg2.util.RGBAColor;


/**
 * @author Albert
 *         <p/>
 *         a pinhole camera
 */
public class Camera extends ADefaultSceneObject implements ICamera {

    /**
     * the position-vector which represents the cameras eye-point-position
     */
    private final Homogeneous3DVector_Double positionVector;

    /**
     * the position-vector which the camera is looking at
     */
    private final Homogeneous3DVector_Double lookAtVector = new Homogeneous3DVector_Double(0, 0, -1, 1);

    /**
     * the direction-vector which represents the cameras translation
     */
    private final Homogeneous3DVector_Double translationVector = new Homogeneous3DVector_Double(0, 0, 0, 0);

    /**
     * the projectionarea's width
     */
    private double projectionAreaWidth;

    /**
     * the projectionarea's height
     */
    private double projectionAreaHeight;

    /**
     * the opening angle of the camera in degree
     */
    private double fieldOfViewX;


    /**
     * a standard constructor
     *
     * @param positionVector       the position-vector which represents the cameras eye-point-position
     * @param lookAtVector         the position-vector which the camera is looking at
     * @param upVector             the direction-Vector which represents the cameras up-direction
     * @param projectionAreaWidth
     * @param projectionAreaHeight
     * @param fieldOfViewX
     */
    public Camera(final Homogeneous3DVector_Double positionVector, final double projectionAreaWidth, final double projectionAreaHeight, final double fieldOfViewX) {
        super();
        this.positionVector = positionVector;
        this.fieldOfViewX = fieldOfViewX;
        this.projectionAreaWidth = projectionAreaWidth;
        this.projectionAreaHeight = projectionAreaHeight;
    }

    /**
     * a complete constructor
     *
     * @param positionX            the positionVectors x value
     * @param positionY            the positionVectors y value
     * @param positionZ            the positionVectors z value
     * @param lookAtX              the lookAtVectors x value
     * @param lookAtY              the lookAtVectors y value
     * @param lookAtZ              the lookAtVectors z value
     * @param upX                  the upVectors x value
     * @param upY                  the upVectors y value
     * @param upZ                  the upVectors z value
     * @param projectionAreaWidth
     * @param projectionAreaHeight
     * @param fieldOfViewX
     */
    public Camera(final double positionX, final double positionY, final double positionZ, final double projectionAreaWidth, final double projectionAreaHeight, final double fieldOfViewX) {
        this(new Homogeneous3DVector_Double(positionX, positionY, positionZ, 1), projectionAreaWidth, projectionAreaHeight, fieldOfViewX);
    }

    /**
     * the default constructor
     */
    public Camera() {
        this(0, 0, 0, 16, 9, 120);
    }

    /**
     * returns the cameras position vector
     *
     * @return the cameras position vector
     */
    public Homogeneous3DVector_Double getPositionVector() {
        return new Homogeneous3DVector_Double(positionVector);
    }

    /**
     * @param newPositionVector
     */
    public void setPositionVector(final Homogeneous3DVector_Double newPositionVector) {
        positionVector.setXYZ(newPositionVector.getX(), newPositionVector.getY(), newPositionVector.getZ());
    }

    /**
     * returns a copy of the cameras lookAtVector
     *
     * @return the cameras lookAtVector
     */
    public Homogeneous3DVector_Double getLookAtVector() {
        return new Homogeneous3DVector_Double(lookAtVector);
    }

    /**
     * returns the fieldOfView-value in degree
     *
     * @return the fieldOfView-value in degree
     */
    public Double getFieldOfView() {
        return fieldOfViewX;
    }

    @Override
    public void translate(final Double xTranslation, final Double yTranslation, final Double zTranslation) {
        this.translationVector.setX(this.translationVector.getX() + xTranslation);
        this.translationVector.setY(this.translationVector.getY() + yTranslation);
        this.translationVector.setZ(this.translationVector.getZ() + zTranslation);
    }

    @Override
    public RGBAColor pixelColorAt(int currentPixelX, int currentPixelY, int imageWidth, int imageHeight) {
        RGBAColor color = Config.backGroudColor;
        Ray ray = generateRay(currentPixelX, currentPixelY, imageWidth, imageHeight);
        Hit hit = ray.findClosestHit(this.getParent());
        if (hit != null) {
            ADefaultSceneObject sceneObject = hit.getHitSceneObject();
            if (sceneObject instanceof IShadeable) {
                IShadeable shadeable = (IShadeable) sceneObject;
                color = shadeable.getMaterial().shade(hit);
            } else {
                color = ((IHasColor) sceneObject).getColor();
            }
        }
        return color;
    }

    @Override
    public Ray generateRay(int currentPixelX, int currentPixelY, int imageWidth, int imageHeight) {
        double rayX = -(projectionAreaWidth / 2) + ((currentPixelX + 0.5) * (projectionAreaWidth / imageWidth));
        double rayY = -(projectionAreaHeight / 2) + ((currentPixelY + 0.5) * (projectionAreaHeight / imageHeight));
        double rayZ = -(projectionAreaWidth / (2 * Math.tan(Math.toRadians(fieldOfViewX / 2))));
        return new Ray(this.getPositionVector(), new Homogeneous3DVector_Double(rayX, rayY, rayZ, 0).normalize());
    }

    @Override
    public Hit findClosestHit(Ray potentialHittingRay) {
        ADefaultSceneObject potentialRaytraceable = this.getRoot();
        Hit currentClosestHit = null;
        return findClosestHit(potentialHittingRay, potentialRaytraceable, currentClosestHit);
    }

    private Hit findClosestHit(Ray potentialHittingRay, ADefaultSceneObject potentialRaytraceable, Hit currentClosestHit) {

        //Just IRaytraceable can be hit by a ray
        if (potentialRaytraceable instanceof IRaytraceable) {
            //The SceneObject is a IRaytraceable
            IRaytraceable raytraceable = (IRaytraceable) potentialRaytraceable;
            //getting the hit object the IRaytraceable object returns
            Hit potentialClosestHit = raytraceable.intersect(potentialHittingRay);
            //checking if the potentialClosestHit is a valid Hit
            if (potentialClosestHit != null && potentialClosestHit.getRayDistanceParameter() >= 0) {
                //checking if the potentialClosestHit is closer then the currentClosestHit
                if (currentClosestHit == null || potentialClosestHit.getRayDistanceParameter() < currentClosestHit.getRayDistanceParameter()) {
                    currentClosestHit = potentialClosestHit;
                }
            }

        }

        //recursive Part. traversing through all children
        for (ADefaultSceneObject sceneObject : potentialRaytraceable.getChildren()) {
            currentClosestHit = findClosestHit(potentialHittingRay, sceneObject, currentClosestHit);
        }

        return currentClosestHit;
    }
}