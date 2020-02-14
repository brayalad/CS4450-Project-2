package com.cpp.cs.cs4450.models.shapes;

import com.cpp.cs.cs4450.graphics.Renderable;

import java.awt.Color;

/**
 * This class is an abstraction of a shape meant to be displayed. It should be extended by any
 * shape that will be displayed. It implements the {@link com.cpp.cs.cs4450.graphics.Renderable} interface.
 */
public abstract class DisplayShape implements Renderable {

    /**
     * The color of the display shape.
     */
    protected Color color;

    /**
     * Abstract Constructor
     *
     * @param color The color of the shape
     */
    public DisplayShape(final Color color){
        this.color = color;
    }

    /**
     * Sets the shapes color
     *
     * @param color the color of the shape
     */
    public void setColor(final Color color){
        this.color = color;
    }

    /**
     * Gets the shapes color
     *
     * @return {@link #color}
     */
    public Color getColor(){ return color; }

    /**
     * Draws the shape onto the screen.
     */
    public abstract void draw();

}

