package cg2.scenegraph.Scenes;

import cg2.scenegraph.ADefaultSceneObject;
import cg2.scenegraph.cameras.Camera;
import cg2.scenegraph.complexforms.SphereCave;
import cg2.scenegraph.lights.DotLight;

public class SphereCaveScene extends ADefaultSceneObject {

    public SphereCaveScene(final double lightStrength) {

        // begin creating scene objects

        Camera camera = new Camera(0, 0, 0, 16, 9, 120);

        DotLight dotLight1 = new DotLight(0, 0, -9);
        dotLight1.setLightFactorRed(lightStrength);
        dotLight1.setLightFactorGreen(lightStrength);
        dotLight1.setLightFactorBlue(lightStrength);

        SphereCave sphereCave = new SphereCave();

        // end creating scene objects
        addChild(camera);

        // begin building the scene

        this.addChild(dotLight1);

        this.addChild(sphereCave);

        // end building the scene

    }

}
