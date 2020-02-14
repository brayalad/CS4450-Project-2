package com.cpp.cs.cs4450.graphics;

import org.lwjgl.opengl.Display;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a skeleton implementation of {@link com.cpp.cs.cs4450.graphics.GraphicsEngine} interface.
 * Implementations of the interface can extend this class to minimize work needed to implement.
 */
public abstract class AbstractGraphicsEngine implements GraphicsEngine {

    /**
     * List of renderable objects for the {@link com.cpp.cs.cs4450.graphics.GraphicsEngine} to render.
     */
    protected final List<Renderable> renders;

    /**
     * Default constructor.
     */
    public AbstractGraphicsEngine(){
        this(new ArrayList<>());
    }

    /**
     * Constructor
     *
     * @param renders List of objects to render.
     */
    public AbstractGraphicsEngine(final List<Renderable> renders){
        this.renders = renders;
    }

    /**
     * Method that renders objects in the {@link #renders} list to the display.
     */
    @Override
    public void render(){
        renders.forEach(Renderable::render);
    }

    /**
     * Method that checks if the display has been closed.
     *
     * @return true of window has been requested to close.
     */
    @Override
    public boolean displayCloseRequested(){
        return Display.isCloseRequested();
    }

    /**
     * Methods that adds a new object to be rendered.
     *
     * @param renderable Object to add to rendered objects.
     *
     * @return true if object was added successfully.
     */
    @Override
    public boolean addRenderable(final Renderable renderable) {
        return renders.add(renderable);
    }

    /**
     * Method that removes an object from the rendered list.
     *
     * @param renderable Object to remove from rendered objects.
     *
     * @return true if object was removed successfully.
     */
    @Override
    public boolean removeRenderable(final Renderable renderable) {
        return renders.remove(renderable);
    }

    /**
     * Method that returns the amount of objects to render.
     *
     * @return size of render objects list.
     */
    @Override
    public int renderAmount() {
        return renders.size();
    }

}

