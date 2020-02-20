/***************************************************************
 * file: Scaling.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: Scales a Transformable object
 *
 ****************************************************************/

package com.cpp.cs.cs4450.models.transformations;

import com.cpp.cs.cs4450.graphics.Transformable;
import com.cpp.cs.cs4450.models.grid.Vertex;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * Scales a Transformable object
 */
public class Scaling extends AbstractTransformation implements Transformation{

    /**
     * scaling x value
     */
    private final double sx;

    /**
     * scaling y value
     */
    private final double sy;

    /**
     * scaling pivot
     */
    private final Entry<Double, Double> pivot;

    /**
     * Start translation
     */
    private final Translation translation;

    /**
     * End translation
     */
    private final Translation recenter;

    /**
     * Constructor
     *
     * @param sx scaling x value
     * @param sy scaling y value
     * @param px pivot x value
     * @param py pivot y value
     */
    public Scaling(final double sx, final double sy, final double px, final double py){
        this(sx, sy, new SimpleEntry<>(px, py));
    }

    /**
     * Constructor
     *
     * @param sx scaling x factor
     * @param sy scaling y factor
     * @param pivot pivot value
     */
    public Scaling(final double sx, final double sy, final Entry<Double, Double> pivot) {
        this.sx = sx;
        this.sy = sy;
        this.pivot = pivot;
        this.translation = new Translation(pivot.getKey(), pivot.getValue());
        this.recenter = new Translation(-pivot.getKey(), - pivot.getValue());
    }

    /**
     * Transforms the transformable
     *
     * @param transformable object to transform
     */
    @Override
    public void transform(final Transformable transformable){
        translation.transform(transformable);
        transform(transformable.getVertices());
        recenter.transform(transformable);
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

        vertex.setX(x * sx);
        vertex.setY(y * sy);
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

        final Scaling other = (Scaling) obj;

        return Objects.equals(this.sx, other.sx) &&
                Objects.equals(this.sy, other.sy) &&
                Objects.equals(this.pivot, other.pivot);
    }

    /**
     * Overrided toString()
     *
     * @return string representation
     */
    @Override
    public String toString(){
        return "\nScaling:\n" +
                "\tX:\t" + sx + "\n" +
                "\tY:\t" + sy + "\n" +
                "\tPivot:\t[" + pivot.getKey() + ", " + pivot.getValue() + "]";
    }

}
