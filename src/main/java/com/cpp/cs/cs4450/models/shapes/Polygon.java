/***************************************************************
 * file: Polygon.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: Represents a list of vertices as a polygon.
 *
 ****************************************************************/

package com.cpp.cs.cs4450.models.shapes;

import com.cpp.cs.cs4450.graphics.Fillable;
import com.cpp.cs.cs4450.graphics.Renderable;
import com.cpp.cs.cs4450.graphics.Transformable;
import com.cpp.cs.cs4450.models.grid.Vertex;
import com.cpp.cs.cs4450.models.transformations.Transformation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a list of vertices as a polygon.
 */
public class Polygon extends DisplayShape implements Transformable, Renderable, Fillable {
    /**
     * Polygon's vertices
     */
    private final List<Vertex> vertices;

    /**
     * Transformations to make
     */
    private final Set<Transformation> transformations;


    /**
     * Constructor
     *
     * @param color color of polygon
     * @param vertices vertices of polygon
     * @param transformations transformations for polygon
     */
    public Polygon(final Color color, final List<Vertex> vertices, final Set<Transformation> transformations) {
        super(color);
        this.vertices = vertices;
        this.transformations = transformations;
    }

    /**
     * Getter for vertices
     *
     * @return vertces
     */
    public List<Vertex> getVertices(){
        return vertices;
    }

    /**
     * Draws the shape onto the screen.
     */
    @Override
    public void draw() {
        drawPolygon();
    }

    /**
     * Function responsible with calculating the necessary calculation needed to render the class
     * onto the computer display.
     */
    @Override
    public void render() {
        drawPolygon();
    }

    /**
     * Draws polygon onto screen
     */
    private void drawPolygon(){
        for(final Line line : getLines()){
            line.draw();
        }
    }

    /**
     * Getter for polygon's lines
     *
     * @return lines
     */
    public List<Line> getLines(){
        final List<Line> lines = new ArrayList<>();

        final LinkedList<Vertex> vertexList = new LinkedList<>(vertices);
        lines.add(new Line(this.color, vertexList.getLast(), vertexList.getFirst()));
        for(int i = 0; i < vertexList.size() - 1; ++i){
            lines.add(new Line(this.color, vertexList.get(i), vertexList.get(i + 1)));
        }

        return lines;
    }

    /**
     * Transforms the Transformable
     */
    @Override
    public void transform(){
        transform(transformations);
    }

    /**
     * Transforms the Transformable
     *
     * @param transformations collections of transformations to do
     */
    @Override
    public void transform(final Collection<Transformation> transformations){
        transformations.forEach(this::transform);
    }

    /**
     * Transforms the Transformable with Transformation
     *
     * @param transformation Transformation to use
     */
    @Override
    public void transform(final Transformation transformation){
        transformation.transform(this);
    }

    /**
     * Overrided toString
     *
     * @return string representation
     */
    @Override
    public String toString(){
        return vertices.toString();
    }

    /**
     * Checks if object is equal
     *
     * @param obj object to check
     *
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(final Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(obj.getClass() != getClass()){
            return false;
        }

        final Polygon other = (Polygon) obj;

        return Objects.equals(this.vertices, other.vertices);
    }

    /**
     * Gets hash code
     *
     * @return hash code
     */
    @Override
    public int hashCode(){
        return Objects.hash(this.vertices);
    }

}
