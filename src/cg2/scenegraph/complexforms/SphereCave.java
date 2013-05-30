package cg2.scenegraph.complexforms;

import cg2.scenegraph.ADefaultSceneObject;
import cg2.scenegraph.Scene;
import cg2.scenegraph.primitives.Sphere;
import cg2.scenegraph.primitives.Triangle;
import cg2.util.Material;
import cg2.util.RGBAColor;

/**
 * @author Albert
 *         <p/>
 *         a complex scene object build of triangles and spheres
 *         its like a prism with some balls on its bottom
 */
public class SphereCave extends ADefaultSceneObject {

    /**
     * the complete constructor
     * instantiates a new sphere cave
     */
    public SphereCave() {
        super();

        Scene triangleScene = new Scene();

        int depthstartvalue = -15;
        int neardepth = depthstartvalue + 6;

        float red = (float) Math.random();
        float green = (float) Math.random();
        float blue = (float) Math.random();

        double emissiveLightFactor = 0; //Math.random();
        Material caveMaterial = new Material(new RGBAColor(red, green, blue), emissiveLightFactor);

        triangleScene.addChild(new Triangle(0, 0, depthstartvalue - 2, 7, 0, depthstartvalue + 6, 0, 7, depthstartvalue + 6, caveMaterial));
        triangleScene.addChild(new Triangle(0, 0, depthstartvalue - 2, 0, 7, depthstartvalue + 6, -7, 0, depthstartvalue + 6, caveMaterial));
        triangleScene.addChild(new Triangle(0, 0, depthstartvalue - 2, 0, -7, depthstartvalue + 6, 7, 0, depthstartvalue + 6, caveMaterial));
        triangleScene.addChild(new Triangle(0, 0, depthstartvalue - 2, -7, 0, depthstartvalue + 6, 0, -7, depthstartvalue + 6, caveMaterial));

        red = (float) Math.random();
        green = (float) Math.random();
        blue = (float) Math.random();

        emissiveLightFactor = 0; //Math.random();
        caveMaterial = new Material(new RGBAColor(red, green, blue), emissiveLightFactor);

        triangleScene.addChild(new Triangle(0, 0, depthstartvalue + 16, 0, 7, depthstartvalue + 6, 7, 0, depthstartvalue + 6, caveMaterial));
        triangleScene.addChild(new Triangle(0, 0, depthstartvalue + 16, -7, 0, depthstartvalue + 6, 0, 7, depthstartvalue + 6, caveMaterial));
        triangleScene.addChild(new Triangle(0, 0, depthstartvalue + 16, 7, 0, depthstartvalue + 6, 0, -7, depthstartvalue + 6, caveMaterial));
        triangleScene.addChild(new Triangle(0, 0, depthstartvalue + 16, 0, -7, depthstartvalue + 6, -7, 0, depthstartvalue + 6, caveMaterial));

        Scene sphereScene = new Scene();

        red = (float) Math.random();
        green = (float) Math.random();
        blue = (float) Math.random();

        RGBAColor color = new RGBAColor(red, green, blue, 1f);

        emissiveLightFactor = 0; //0.3 * Math.random();

        sphereScene.addChild(new Sphere(0, 0, depthstartvalue, 1, new Material(color, Math.min(emissiveLightFactor * 2, 1))));

        for (int i = -1; i >= (depthstartvalue - neardepth) / 2; i--) {
            sphereScene.addChild(new Sphere(-i, i, depthstartvalue - i * 2, 0.5, new Material(color, emissiveLightFactor)));
            sphereScene.addChild(new Sphere(-i, -i, depthstartvalue - i * 2, 0.5, new Material(color, emissiveLightFactor)));
            sphereScene.addChild(new Sphere(i, i, depthstartvalue - i * 2, 0.5, new Material(color, emissiveLightFactor)));
            sphereScene.addChild(new Sphere(i, -i, depthstartvalue - i * 2, 0.5, new Material(color, emissiveLightFactor)));
        }

        red = (float) Math.random();
        green = (float) Math.random();
        blue = (float) Math.random();

        color = new RGBAColor(red, green, blue, 1f);

        emissiveLightFactor = 0; //0.3 * Math.random();

        for (int i = -2; i >= depthstartvalue - neardepth; i -= 2) {
            sphereScene.addChild(new Sphere(i, 0, depthstartvalue - i, 0.5, new Material(color, emissiveLightFactor)));
            sphereScene.addChild(new Sphere(-i, 0, depthstartvalue - i, 0.5, new Material(color, emissiveLightFactor)));
            sphereScene.addChild(new Sphere(0, i, depthstartvalue - i, 0.5, new Material(color, emissiveLightFactor)));
            sphereScene.addChild(new Sphere(0, -i, depthstartvalue - i, 0.5, new Material(color, emissiveLightFactor)));
        }

        this.addChild(triangleScene);
        this.addChild(sphereScene);
    }

}
