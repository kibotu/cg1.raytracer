package cg2.scenegraph.complexforms;

import cg2.scenegraph.ADefaultSceneObject;
import cg2.scenegraph.primitives.Cuboid;
import cg2.util.Material;
import cg2.util.RGBAColor;

/**
 * @author Albert
 *         <p/>
 *         a complex scene object build of cuboid primitives
 */
public class CuboidGrid extends ADefaultSceneObject {

    /**
     * complete constructor
     *
     * @param centerX  the x position of the objects center
     * @param centerY  the y position of the objects center
     * @param centerZ  the z position of the objects center
     * @param gridSize the number of cuboids per row and column
     */
    public CuboidGrid(double centerX, double centerY, double centerZ, int gridSize) {
        super();

        final double width = 0.5;
        final double hight = 0.5;
        final double depth = 0.5;

        for (double x = centerX - gridSize / 2.0 + 0.5; x <= centerX + gridSize / 2.0 - 0.5; x++) {
            for (double y = centerY - gridSize / 2.0 + 0.5; y <= centerY + gridSize / 2.0 - 0.5; y++) {
                for (double z = centerZ - gridSize / 2.0 + 0.5; z <= centerZ + gridSize / 2.0 - 0.5; z++) {
                    this.addChild(new Cuboid(x, y, z, width, hight, depth, new Material(new RGBAColor(), 0)));
                }
            }
        }
    }

}
