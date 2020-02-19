/***************************************************************
 * file: Vertex.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: Pair of coordinates for a polygon
 *
 ****************************************************************/
package com.cpp.cs.cs4450.models.grid;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * Pair class to contain coordinates
 */
public class Vertex extends SimpleEntry<Double, Double> implements Entry<Double, Double>, Comparable<Vertex> {
    /**
     * X-Coordinate
     */
    private double key;

    /**
     * Constructor
     *
     * @param key X-Coordinate
     * @param value Y-Coordinate
     */
    public Vertex(final Double key, final Double value) {
        super(key, value);
        this.key = key;
    }

    /**
     * Constructor
     *
     * @param entry coordinate pair
     */
    public Vertex(Entry<? extends Double, ? extends Double> entry) {
        this(entry.getKey(), entry.getValue());
    }

    /**
     * Static constructor
     *
     * @param x x-coordinate
     * @param y y-coordinate
     *
     * @return new Vertex
     */
    public static Vertex of(final Double x, final Double y){
        return new Vertex(x, y);
    }

    /**
     * Getter for key
     *
     * @return key
     */
    @Override
    public Double getKey(){ return key; }

    /**
     * Setter for key
     *
     * @param key new key
     */
    public void setKey(final Double key){
        this.key = key;
    }

    /**
     * Getter for X
     * @return x
     */
    public Double getX(){ return getKey(); }

    /**
     * Setter for X
     *
     * @param x new x
     */
    public void setX(final Double x){
        this.key = x;
    }

    /**
     * Getter for Y
     *
     * @return y
     */
    public Double getY(){ return getValue(); }

    /**
     * Setter for Y
     *
     * @param value new y valuse
     */
    public void setY(final Double value){
        setValue(value);
    }

    /**
     * Compares vertex to another vertes
     *
     * @param other vertex to compare to
     *
     * @return 0 if equal, 1 if greater, -1 if less than
     */
    @Override
    public int compareTo(final Vertex other) {
        if(this.getY() < other.getY()){
            return -1;
        } else if(this.getY().equals(other.getY())){
            return Double.compare(this.getX(), other.getX());
        } else {
            return 1;
        }
    }

    /**
     * Checks if object is equal
     *
     * @param obj object to compare
     *
     * @return true if equal
     */
    @Override
    public boolean equals(final Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(obj.getClass() != getClass()){
            return false;
        }

        final Vertex other = (Vertex) obj;

        return Objects.equals(this.getX(), other.getX()) && Objects.equals(this.getY(), other.getY());
    }

    /**
     * Overrided toString()
     *
     * @return string representation
     */
    @Override
    public String toString(){
        return "[" + getX() + ", " + getY() + "]";
    }

}
