package cg2.scenegraph.itemabilities;

import cg2.math.Homogeneous3DVector_Double;
import cg2.util.IMaterial;


public interface IShadeable {

    /**
     * returns the objects material
     *
     * @return the objects material
     */
    public IMaterial getMaterial();

    /**
     * calculates the objects normal at the specified position
     *
     * @param position the position at the object at which the normal have to be
     * @return the normal at the specified position
     */
    public Homogeneous3DVector_Double calculateNormalAtPosition(Homogeneous3DVector_Double position);

}
