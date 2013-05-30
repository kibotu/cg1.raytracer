package cg2.scenegraph;

import cg2.scenegraph.cameras.Camera;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Albert
 */
public abstract class ADefaultSceneObject {

    /**
     * a counter which represents the total number of created scene objects
     */
    public static int instanceCounter = 0;

    /**
     * the null scene sense is to avoid the null value
     */
    public static final ADefaultSceneObject nullScene = new ADefaultSceneObject() {
    };

    /**
     * represents the number of this instance in the row of all instances
     */
    public final int instanceNumber;

    /**
     * a list which contains all children of this scene object
     */
    private final List<ADefaultSceneObject> children = new ArrayList<ADefaultSceneObject>();

    /**
     * the parent of this object
     * if it has no parent than the parent will be the null scene
     */
    private ADefaultSceneObject parent = nullScene;

    /**
     * the complete constructor
     */
    protected ADefaultSceneObject() {
        super();
        ADefaultSceneObject.instanceCounter++;
        this.instanceNumber = instanceCounter;
    }

    /**
     * Adds a scene object as child to this Scene-Object
     *
     * @param newChild the scene object to add as child
     */
    public void addChild(final ADefaultSceneObject newChild) {
        if (newChild.parent != nullScene) {
            throw (new IllegalStateException(newChild + " allready is " + newChild.parent + "'s child! A scene-object can just be child of one other scene-object."));
        }
        if (newChild == this) {
            throw (new IllegalStateException("You tryed to add " + this + " to itself as child! Bad guy, thats not possible."));
        }
        if (newChild == this.getRoot()) {
            throw (new IllegalStateException("You tryed to add " + this + "'s root-scene-object as child! Don't bulid circles!"));
        }

        children.add(newChild);
        newChild.addParent(this);
    }

    /**
     * Removes a Child-{@link ADefaultSceneObject} from this Scene-Object
     *
     * @param oldChild the Child-{@link ADefaultSceneObject} to remove
     */
    public void removeChild(final ADefaultSceneObject oldChild) {
        children.remove(oldChild);
        oldChild.removeParent();
    }

    ;

    /**
     * Adds a scene object as Parent to this Scene-Object
     *
     * @param newParent the scene object to add as parent
     */
    protected void addParent(final ADefaultSceneObject newParent) {
        if (parent != nullScene) {
            throw (new IllegalStateException(this + " allready has a parent! A scene-object can just have one parent."));
        }
        if (newParent == this) {
            throw (new IllegalStateException("You tryed to add " + this + " to itself as parent! Bad guy, thats not possible."));
        }
        if (newParent.hasChild(this)) {
            parent = newParent;
        } else {
            throw (new IllegalStateException("It's not legal to add " + this + " to " + newParent + " becouse " + this + " is no Child of " + newParent + "."));
        }
    }

    ;

    /**
     * removes the scene objects parent
     */
    protected void removeParent() {
        if (parent == nullScene) {
            throw (new IllegalStateException("There is no parent to remove. Seems like something goes wrong"));
        }
        parent = nullScene;
    }

    ;

    /**
     * Returns true if the specified scene object is a child of this.
     *
     * @param potenzialChild the scene object you want to know if it is a child of this
     * @return true if the specified scene object is a child of this
     */
    protected boolean hasChild(final ADefaultSceneObject potenzialChild) {
        return children.contains(potenzialChild);
    }

    ;

    /**
     * returns the scene object's root-scene object
     *
     * @return the scene object's root-scene object
     */
    public ADefaultSceneObject getRoot() {
        return (this.parent != nullScene) ? this.parent.getRoot() : this;
    }

    /**
     * returns the parent.
     *
     * @return the parent.
     */
    public ADefaultSceneObject getParent() {
        return parent;
    }

    /**
     * returns a list with all children. The list is empty if this have no children
     *
     * @return a list with all children
     */
    public List<ADefaultSceneObject> getChildren() {
        return children;
    }

    /**
     * returns a list filled with all instances from root to all leafs of this objects tree which are a subclass of the specified class
     *
     * @param c the class of which the filtered instances should be subclasses
     * @return the list to which the filtered instances are added
     */
    public <T> List<T> getAllOfTypeX(Class<T> c) {
        List<T> list = new ArrayList<T>();
        this.getRoot().getAllOfTypeXFromHere(list, c);
        return list;
    }

    /**
     * fills the specified list with all instances from root to all leafs of this objects tree which are a subclass of the specified class
     *
     * @param list the list to which the filtered instances should be added
     * @param c    the class of which the filtered instances should be subclasses
     */
    public <T> void getAllOfTypeX(List<T> list, Class<T> c) {
        this.getRoot().getAllOfTypeXFromHere(list, c);
    }

    /**
     * returns a list filled with all children and theirs children and so on which are a subclass of the specified class
     *
     * @param the class of which the filtered instances should be subclasses
     * @return the list to which the filtered instances should be added
     */
    public <T> List<T> getAllOfTypeXFromHere(Class<T> c) {
        List<T> list = new ArrayList<T>();
        this.getAllOfTypeXFromHere(list, c);
        return list;
    }

    /**
     * fills the specified list with all children and theirs children and so on which are a subclass of the specified class
     *
     * @param list the list to which the filtered instances should be added
     * @param c    the class of which the filtered instances should be subclasses
     */
    @SuppressWarnings("unchecked")
    public <T> void getAllOfTypeXFromHere(List<T> list, Class<T> c) {
        if (c.isInstance(this)) {
            list.add((T) this);
        }
        for (ADefaultSceneObject child : this.children) {
            child.getAllOfTypeXFromHere(list, c);
        }
    }


    /**
     * traverse recursive through all children and builds the scene-graph as string
     *
     * @return the scene graph as string
     */
    public String buildTree() {
        return buildTree("", "");
    }

    /**
     * traverse recursive through all children and builds the scene-graph as string
     *
     * @param tree  already existing part of the tree
     * @param space the space before each element
     * @return the given tree extended by this element and it�s children
     */
    private String buildTree(String tree, final String space) {
        String spacer = "|  ";
        tree += space + "scene-object_" + instanceNumber + "(" + this.getClass().getSimpleName() + ")\n";
        for (ADefaultSceneObject child : this.children) {
            tree = child.buildTree(tree, space + spacer);
        }
        return tree;
    }

    @Override
    final public String toString() {
        return "scene-object_" + instanceNumber + "(" + this.getClass().getSimpleName() + ")";
    }

}
