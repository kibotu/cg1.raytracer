package cg2.scenegraph.Scenes;

import cg2.scenegraph.ADefaultSceneObject;
import cg2.scenegraph.cameras.Camera;
import cg2.scenegraph.complexforms.CuboidGrid;
import cg2.scenegraph.lights.DotLight;
import cg2.scenegraph.primitives.Cuboid;
import cg2.scenegraph.primitives.Plane;
import cg2.scenegraph.primitives.Sphere;
import cg2.util.Material;
import cg2.util.RGBAColor;

public class AllObjectsScene extends ADefaultSceneObject {

    public AllObjectsScene(final boolean secondLightOn) {

        Camera camera = new Camera(0, 0, 0, 16, 9, 120);

        /** planes **/

        // bottom
        Plane plane1 = new Plane(0, -2, 0, 0, 1, 0, new Material(new RGBAColor(true), 0, 800, 1));
        // top
        Plane plane2 = new Plane(0, 2, 0, 0, -1, 0, new Material(new RGBAColor(true), 0, 800, 1));

        /** cubes **/

        // left
        Cuboid cube1 = new Cuboid(-4,0,-(Math.random()+7),Math.random(),Math.random(),Math.random());

        // right
        Cuboid cube2 = new Cuboid(4,0,-(Math.random()+7),Math.random(),Math.random(),Math.random());

        /** spheres **/

        Sphere sphere1 = new Sphere(Math.random(), Math.random(), -(Math.random()+5), Math.random(), new Material(new RGBAColor(true), 0));

        Sphere sphere2 = new Sphere(-Math.random(), Math.random(), -(Math.random()+5), Math.random(), new Material(new RGBAColor(true), 0));

        Sphere sphere3 = new Sphere(-Math.random(), -Math.random(), -(Math.random()+5), Math.random(), new Material(new RGBAColor(true), 0));

        Sphere sphere4 = new Sphere(Math.random(), -Math.random(), -(Math.random()+5), Math.random(), new Material(new RGBAColor(true), 0));


        DotLight dotLight1 = new DotLight(0, 0, -2);
        dotLight1.setLightFactorRed(0.4);
        dotLight1.setLightFactorGreen(0.4);
        dotLight1.setLightFactorBlue(0.4);

        DotLight dotLight2 = new DotLight(0, 0, -5);
        dotLight2.setLightFactorRed(0.4);
        dotLight2.setLightFactorGreen(0.4);
        dotLight2.setLightFactorBlue(0.4);

        addChild(camera);

        addChild(dotLight1);

        if (secondLightOn) {
            addChild(dotLight2);
        }

        addChild(sphere1);
        addChild(sphere2);
        addChild(sphere3);
        addChild(sphere4);
        addChild(plane1);
        addChild(plane2);
        addChild(cube1);
        addChild(cube2);
    }
}
