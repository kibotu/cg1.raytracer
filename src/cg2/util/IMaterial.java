package cg2.util;

import cg2.raytracer.Hit;

public interface IMaterial {

    /**
     * returns the materials color
     *
     * @return the materials color
     */
    public RGBAColor getMaterialColor();

    /**
     * calculates the color for the specified hit
     *
     * @param hit the hit which contains the informations to calculate the color
     * @return the calculated color
     */
    public RGBAColor shade(Hit hit);

}
