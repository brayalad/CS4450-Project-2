/***************************************************************
 * file: Renderable.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: Interface for objects to implement to be rendered to display
 *
 ****************************************************************/

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