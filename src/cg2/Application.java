package cg2;

import cg2.imagehandler.ImageHandler;
import cg2.scenegraph.Scene;
import cg2.scenegraph.Scenes.AllObjectsScene;
import cg2.scenegraph.Scenes.SphereCaveScene;
import cg2.scenegraph.cameras.Camera;
import cg2.util.Config;
import cg2.util.RGBAColor;

import javax.swing.*;
import java.awt.image.BufferedImage;


public class Application {

    public static void main(String[] args) {

        try {

            Config.backGroudColor = new RGBAColor(RGBAColor.BLACK);

            Long time = System.nanoTime();

            Scene scene = new Scene();

            //Here are three different scenes
            //there most attributes are set randomly so the results will look different each time
            //only add one of these tree scenes otherwise the result will be chaotic
            //there are set some default values for nice look (may you need some tries because the random attributes) and acceptable calculating time

//            AreaLightScene areaLightScene = new AreaLightScene(0.2, 0.2, 5, 5);
//            Config.recursionDepth = 0;
//            Config.depugModeOn = false;
//            scene.addChild(areaLightScene);

//			MirrorChamberScene mirrorChamberScene = new MirrorChamberScene(true);
//			Config.recursionDepth = 10;
//			scene.addChild(mirrorChamberScene);

			SphereCaveScene sphereCaveScene = new SphereCaveScene(4);
			Config.recursionDepth = 5;
			scene.addChild(sphereCaveScene);

            //AllObjectsScene allObjectsScene = new AllObjectsScene(true);
            //Config.recursionDepth = 5;
            //scene.addChild(allObjectsScene);

            Camera camera = new Camera(0, 0, 0, 16, 9, 90);
            scene.addChild(camera);

            System.out.println(scene.buildTree());

            ImageHandler imageHandler = new ImageHandler(camera, 16 * 64, 9 * 64);
            BufferedImage bufferedImage = imageHandler.generateBufferedImage();
            ImageHandler.saveImage(bufferedImage, "image" + bufferedImage.hashCode() + ".png", "png");
            ImageHandler.openImage("image" + bufferedImage.hashCode() + ".png");

            time = System.nanoTime() - time;
            System.out.println(time / 1000000000.0 + "sec");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Dialog", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }
    }
}