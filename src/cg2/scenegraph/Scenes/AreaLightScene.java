package cg2.scenegraph.Scenes;

import cg2.scenegraph.ADefaultSceneObject;
import cg2.scenegraph.cameras.Camera;
import cg2.scenegraph.lights.AreaLight;
import cg2.scenegraph.primitives.Plane;
import cg2.scenegraph.primitives.Sphere;
import cg2.util.Material;
import cg2.util.RGBAColor;

public class AreaLightScene extends ADefaultSceneObject {

    public AreaLightScene(final double hight, final double width, final int numberOfRows, final int numberOfColumns) {

        // begin creating scene objects


        Camera camera = new Camera(0, 0, 0, 16, 9, 120);

        AreaLight areaLight = new AreaLight(0, 2, -4, 0, -1, 0, 2, 2, 2, hight, width, numberOfRows, numberOfColumns);

        Plane plane1 = new Plane(0, -2, 0, 0, -1, 0, new Material(new RGBAColor(RGBAColor.WHITE), 0));
        Plane plane2 = new Plane(0, 10, 0, 0, -1, 0, new Material(new RGBAColor(RGBAColor.BLACK), 0, 0, 0));

        Sphere sphere = new Sphere(0, 0, -4, 1, new Material(new RGBAColor(), 0));

        // begin creating scene objects

        // begin building the scene

        addChild(camera);

        this.addChild(plane1);
        this.addChild(plane2);

        plane1.addChild(sphere);

        plane1.addChild(areaLight);

        // end building the scene
    }

}
