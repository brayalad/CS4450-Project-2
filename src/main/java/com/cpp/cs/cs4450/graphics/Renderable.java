package com.cpp.cs.cs4450.graphics;

/**
 * Functional Interface with a single render method. This interface must be implemented by any class that
 * will be rendered by the {@link com.cpp.cs.cs4450.graphics.GraphicsEngine} interface when rendering
 * computer graphics to the computer display
 */
@FunctionalInterface
public interface Renderable {

    /**
     * Function responsible with calculating the necessary calculation needed to render the class
     * onto the computer display.
     */
    void render();

}