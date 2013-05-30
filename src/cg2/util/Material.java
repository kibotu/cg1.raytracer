package cg2.util;

import cg2.math.Homogeneous3DVector_Double;
import cg2.math.Homogeneous3DVector_Double_Math;
import cg2.raytracer.Hit;
import cg2.raytracer.Ray;
import cg2.scenegraph.ADefaultSceneObject;
import cg2.scenegraph.itemabilities.IHasColor;
import cg2.scenegraph.itemabilities.IShadeable;
import cg2.scenegraph.lights.AmbientLight;
import cg2.scenegraph.lights.DotLight;

import java.util.List;

public class Material implements IMaterial {

    /**
     * the materials color
     */
    private final RGBAColor materialColor;

    /**
     * the materials emissive light factor
     */
    private final double emissiveLightFactor;

    /**
     * the materials phongExponent. Specifies the high light size. Higher value ^= smaller high light
     */
    private final double phongExponent;

    /**
     * the materials reflexionFactor.
     */
    private final double reflectionFactor;

    /**
     * complete constructor
     *
     * @param materialColor       the materials color
     * @param emissiveLightFactor the materials emissive light factor
     * @param phongExponent       the materials phongExponent. Specifies the high light size. Higher value ^= smaller high light
     * @param reflectionFactor    the materials reflection factor
     */
    public Material(final RGBAColor materialColor, final double emissiveLightFactor, final double phongExponent, final double reflectionFactor) {
        if (emissiveLightFactor < 0 || emissiveLightFactor > 1) {
            throw (new IllegalArgumentException("The materials emissive-light-factor is " + emissiveLightFactor + " but it have to be between inclusive 0 and 1."));
        }
        if (phongExponent < 0) {
            throw (new IllegalArgumentException("The materials phong-exponent is " + phongExponent + " but it have to be over 0."));
        }
        if (reflectionFactor < 0 || reflectionFactor > 1) {
            throw (new IllegalArgumentException("The materials reflection-factor is " + reflectionFactor + " but it have to be between inclusive 0 and 1."));
        }
        this.materialColor = materialColor;
        this.emissiveLightFactor = emissiveLightFactor;
        this.phongExponent = phongExponent;
        this.reflectionFactor = reflectionFactor;
    }

    /**
     * customized constructor. the missing values will be set randomly.
     *
     * @param materialColor       the materials color
     * @param emissiveLightFactor the materials emissive light factor
     * @param phongExponent       the materials phongExponent. Specifies the high light size. Higher value ^= smaller high light
     */
    public Material(final RGBAColor materialColor, final double emissiveLightFactor, final double phongExponent) {
        this(materialColor, emissiveLightFactor, phongExponent, Math.random());
    }

    /**
     * customized constructor. the missing values will be set randomly.
     *
     * @param materialColor       the materials color
     * @param emissiveLightFactor the materials emissive light factor
     */
    public Material(final RGBAColor materialColor, final double emissiveLightFactor) {
        this(materialColor, emissiveLightFactor, Math.random() * 600, Math.random());
    }

    /**
     * customized constructor. the missing values will be set randomly.
     *
     * @param materialColor the materials color
     */
    public Material(final RGBAColor materialColor) {
        this(materialColor, Math.random(), Math.random() * 600, Math.random());
    }

    /**
     * customized constructor. the missing values will be set randomly.
     *
     * @param materialColor the materials color
     */
    public Material() {
        this(new RGBAColor(true), Math.random(), Math.random() * 600, Math.random());
    }

    /**
     * returns the materials color
     *
     * @return the materials color
     */
    public RGBAColor getMaterialColor() {
        return new RGBAColor(materialColor.asRGBAInt());
    }

    @Override
    public RGBAColor shade(Hit hit) {
        return this.shade(hit, Config.recursionDepth);
    }

    private RGBAColor shade(final Hit hit, final int recursionDepth) {
        float red = 0;
        float green = 0;
        float blue = 0;

        Homogeneous3DVector_Double hitPosition = hit.getHitPosition();
        Homogeneous3DVector_Double hitNormal = hit.getHitNormal();

        //the materials emissive Light
        red += materialColor.getRed() * emissiveLightFactor;
        green += materialColor.getGreen() * emissiveLightFactor;
        blue += materialColor.getBlue() * emissiveLightFactor;

        ADefaultSceneObject root = hit.getHitSceneObject().getRoot();
        List<AmbientLight> ambientLights = root.getAllOfTypeX(AmbientLight.class);
        List<DotLight> dotLights = root.getAllOfTypeX(DotLight.class);

        for (AmbientLight ambientLight : ambientLights) {
            red += materialColor.getRed() * ambientLight.getAmbientLightFactorRed();
            green += materialColor.getGreen() * ambientLight.getAmbientLightFactorGreen();
            blue += materialColor.getBlue() * ambientLight.getAmbientLightFactorBlue();
        }


        for (DotLight dotLight : dotLights) {

            Homogeneous3DVector_Double shadowRayDirectionUnnormalized = Homogeneous3DVector_Double_Math.subtract(dotLight.getPositionVector(), hitPosition);
            Homogeneous3DVector_Double shadowRayDirection = shadowRayDirectionUnnormalized.normalize();

            Ray shadowRay = new Ray(Homogeneous3DVector_Double_Math.add(hitPosition, Homogeneous3DVector_Double_Math.scalarMultiplicate(0.000000000001, shadowRayDirection)), shadowRayDirection);

            Hit shadowRayClosestHit = shadowRay.findClosestHit(hit.getHitSceneObject().getRoot());

            double hitAndLightDistance = shadowRayDirectionUnnormalized.calculateLength();

            if (shadowRayClosestHit == null || shadowRayClosestHit.getRayDistanceParameter() <= 0 || shadowRayClosestHit.getRayDistanceParameter() >= hitAndLightDistance) {

                double distanceFactor = 1 / Math.pow(hitAndLightDistance, 2);
                double diffuseAngleFactor = Homogeneous3DVector_Double_Math.scalarProdukt(shadowRayDirection, hitNormal);

                //diffuse Light
                if (diffuseAngleFactor > 0) {
                    red += materialColor.getRed() * dotLight.getLightFactorRed() * diffuseAngleFactor * distanceFactor;
                    green += materialColor.getGreen() * dotLight.getLightFactorGreen() * diffuseAngleFactor * distanceFactor;
                    blue += materialColor.getBlue() * dotLight.getLightFactorBlue() * diffuseAngleFactor * distanceFactor;
                }

                Homogeneous3DVector_Double lightReflectionDirection = Homogeneous3DVector_Double_Math.subtract(Homogeneous3DVector_Double_Math.scalarMultiplicate((2 * diffuseAngleFactor), hitNormal), shadowRayDirection);
                Homogeneous3DVector_Double formHitToEyeDirection = Homogeneous3DVector_Double_Math.scalarMultiplicate(-1, hit.getHittingRay().getDirection());
                double specularAngleFactor = Homogeneous3DVector_Double_Math.scalarProdukt(lightReflectionDirection, formHitToEyeDirection);

                //specular Light
                if (specularAngleFactor > 0 && diffuseAngleFactor > 0) {
                    red += materialColor.getRed() * dotLight.getLightFactorRed() * Math.pow(specularAngleFactor, phongExponent) * distanceFactor;
                    green += materialColor.getGreen() * dotLight.getLightFactorGreen() * Math.pow(specularAngleFactor, phongExponent) * distanceFactor;
                    blue += materialColor.getBlue() * dotLight.getLightFactorBlue() * Math.pow(specularAngleFactor, phongExponent) * distanceFactor;
                }

                if (recursionDepth > 0) {
                    double reflectionAngle = Homogeneous3DVector_Double_Math.scalarProdukt(formHitToEyeDirection, hitNormal);
                    Homogeneous3DVector_Double reflectoinDirection = Homogeneous3DVector_Double_Math.subtract(Homogeneous3DVector_Double_Math.scalarMultiplicate((2 * reflectionAngle), hitNormal), formHitToEyeDirection);
                    Ray reflectionRay = new Ray(Homogeneous3DVector_Double_Math.add(hitPosition, Homogeneous3DVector_Double_Math.scalarMultiplicate(0.000000000001, reflectoinDirection)), reflectoinDirection);
                    Hit reflectionHit = reflectionRay.findClosestHit(root);
                    if (reflectionHit != null) {
                        ADefaultSceneObject sceneObject = reflectionHit.getHitSceneObject();
                        if (sceneObject instanceof IShadeable) {
                            IMaterial iMaterial = ((IShadeable) sceneObject).getMaterial();
                            if (iMaterial instanceof Material) {
                                RGBAColor color = ((Material) iMaterial).shade(reflectionHit, recursionDepth - 1);
                                red += red + color.getRed() * reflectionFactor;
                                green += green + color.getGreen() * reflectionFactor;
                                blue += blue + color.getBlue() * reflectionFactor;
                            }
                        } else {
                            RGBAColor color = ((IHasColor) sceneObject).getColor();
                            red += red + color.getRed() * reflectionFactor * distanceFactor;
                            green += green + color.getGreen() * reflectionFactor * distanceFactor;
                            blue += blue + color.getBlue() * reflectionFactor * distanceFactor;
                        }
                    }
                }

            }

        }

        return new RGBAColor(Math.min(red, 1), Math.min(green, 1), Math.min(blue, 1));
    }
}
