/***************************************************************
 * file: Translate.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: Translates a Transformable object
 *
 ****************************************************************/

package com.cpp.cs.cs4450.models.transformations;

import com.cpp.cs.cs4450.models.grid.Vertex;

import java.util.Map.Entry;
import java.util.Objects;

/**
 * Translates a Transformable object
 */
public class Translation extends AbstractTransformation implements Transformation {

    /**
     * Translation x value
     */
    private final double dx;

    /**
     * Translation y value
     */
    private final double dy;

    /**
     * Constructor
     *
     * @param dx translation x value
     * @param dy translation y value
     */
    public Translation(final double dx, final double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructor
     *
     * @param entry tranlsation value
     */
    public Translation(final Entry<Double, Double> entry){
        this(entry.getKey(), entry.getValue());
    }

    /**
     * Transforms a vertex
     *
     * @param vertex vertex to transform
     */
    @Override
    protected void transform(final Vertex vertex){
        final double x = vertex.getX();
        final double y = vertex.getY();

        vertex.setX(x + dx);
        vertex.setY(y + dy);
    }

    /**
     * Checks if object is equal
     *
     * @param obj object to check
     *
     * @return true if equal
     */
    @Override
    public boolean equals(final Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(getClass() != obj.getClass()){
            return false;
        }

        final Translation other = (Translation) obj;

        return Objects.equals(this.dx, other.dx) &&
                Objects.equals(this.dy, other.dy);
    }

    /**
     * Overrided toString()
     *
     * @return string representation
     */
    @Override
    public String toString(){
        return "Translation:\n" +
                "\tX:\t" + dx + "\n" +
                "\tY:\t" + dy + "\n";
    }

}
