/***************************************************************
 * file: Rotate.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: Rotates a Transformable object
 *
 ****************************************************************/

package com.cpp.cs.cs4450.models.transformations;

import com.cpp.cs.cs4450.graphics.Transformable;
import com.cpp.cs.cs4450.models.grid.Vertex;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * Rotates a Transformable object
 */
public class Rotation extends AbstractTransformation implements Transformation {

    /**
     * Angle to rotate by
     */
    private final double angle;

    /**
     * Theta angle to rotate by
     */
    private final double theta;

    /**
     * Pivot to rotate by
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
     * @param angle rotation angle
     * @param px x coordinate pivot
     * @param py y coordinate pivot
     */
    public Rotation(final double angle, final double px, final double py){
        this(angle, new SimpleEntry<>(px,py));
    }

    /**
     * Constructor
     *
     * @param angle rotation angle
     * @param pivot pivot point
     */
    public Rotation(final double angle, final Entry<Double, Double> pivot) {
        this.angle = angle;
        this.theta = Math.toRadians(angle);
        this.pivot = pivot;
        this.translation = new Translation(pivot.getKey(), pivot.getValue());
        this.recenter = new Translation(-pivot.getKey(), - pivot.getValue());
    }

    /**
     * Getter for rotation angle
     *
     * @return rotation angle
     */
    public double getAngle(){ return angle; }

    /**
     * Getter for theta rotation angle
     *
     * @return theta rotation angle
     */
    public double getTheta() {
        return theta;
    }

    /**
     * Getter for pivot point
     *
     * @return pivot
     */
    public Entry<Double, Double> getPivot() {
        return pivot;
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

        vertex.setX(x * Math.cos(theta) - y * Math.sin(theta));
        vertex.setY(x * Math.sin(theta) + y * Math.cos(theta));
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

        final Rotation other = (Rotation) obj;

        return Objects.equals(this.theta, other.theta) &&
                Objects.equals(this.pivot, other.pivot);
    }

    /**
     * Overrided toString()
     *
     * @return string representation
     */
    @Override
    public String toString(){
        return "\nRotation:\n" +
                "\tAngle:\t" + angle + "\n" +
                "\tPivot:\t[" + pivot.getKey() + ", " + pivot.getValue() + "]";
    }

}
