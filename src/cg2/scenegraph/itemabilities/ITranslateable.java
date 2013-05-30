package cg2.scenegraph.itemabilities;


public interface ITranslateable<T extends Number> {

    /**
     * translates the object in the x, y, z direction
     *
     * @param xTranslation
     * @param yTranslation
     * @param zTranslation
     */
    public void translate(final T xTranslation, final T yTranslation, final T zTranslation);

}
