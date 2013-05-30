package cg2.scenegraph.Scenes;

import cg2.scenegraph.ADefaultSceneObject;
import cg2.scenegraph.cameras.Camera;
import cg2.scenegraph.lights.DotLight;
import cg2.scenegraph.primitives.Plane;
import cg2.scenegraph.primitives.Sphere;
import cg2.util.Material;
import cg2.util.RGBAColor;

public class MirrorChamberScene extends ADefaultSceneObject {

    /**
     * @param secondLightOn activates a second lightsource if true
     */
    public MirrorChamberScene(final boolean secondLightOn) {

        Camera camera = new Camera(0, 0, 0, 16, 9, 120);

        Plane plane1 = new Plane(0, -1, 0, 0, 1, 0, new Material(new RGBAColor(RGBAColor.BLACK), 0, 800, 1));
        Plane plane2 = new Plane(2, 0, 0, -1, 0, 0, new Material(new RGBAColor(RGBAColor.BLACK), 0, 800, 1));
        Plane plane3 = new Plane(-2, 0, 0, 1, 0, 0, new Material(new RGBAColor(RGBAColor.BLACK), 0, 800, 1));
        Plane plane4 = new Plane(0, 0, -7, 0, 0, 1, new Material(new RGBAColor(RGBAColor.BLACK), 0, 800, 1));
        Sphere sphere1 = new Sphere(1, 1, -3, 1, new Material(new RGBAColor(true), 0));
        Sphere sphere2 = new Sphere(-1, 0, -4, 1, new Material(new RGBAColor(true), 0));

        DotLight dotLight1 = new DotLight(0, 0, -2);
        dotLight1.setLightFactorRed(0.4);
        dotLight1.setLightFactorGreen(0.4);
        dotLight1.setLightFactorBlue(0.4);

        DotLight dotLight2 = new DotLight(0, 0, -5);
        dotLight2.setLightFactorRed(0.4);
        dotLight2.setLightFactorGreen(0.4);
        dotLight2.setLightFactorBlue(0.4);

        addChild(camera);

        this.addChild(dotLight1);

        if (secondLightOn) {
            this.addChild(dotLight2);
        }

        this.addChild(plane1);
        this.addChild(plane2);
        this.addChild(plane3);
        this.addChild(plane4);
        this.addChild(sphere1);
        this.addChild(sphere2);
    }

}
