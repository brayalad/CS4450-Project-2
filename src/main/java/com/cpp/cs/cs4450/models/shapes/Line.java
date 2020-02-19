/***************************************************************
 * file: Line.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: Model class for a Line
 *
 ****************************************************************/

package com.cpp.cs.cs4450.models.shapes;

import com.cpp.cs.cs4450.graphics.Renderable;
import com.cpp.cs.cs4450.models.grid.Vertex;
import org.lwjgl.opengl.GL11;

import java.awt.Color;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * This class is a model for a ellipse. It extends the {@link com.cpp.cs.cs4450.models.shapes.DisplayShape} abstract class
 * and implements the {@link com.cpp.cs.cs4450.graphics.Renderable} interface.
 */
public class Line extends DisplayShape implements Renderable {

    /**
     * Start coordinates
     */
    private Vertex start;

    /**
     * End coordinates
     */
    private Vertex end;

    /**
     * Constructor
     *
     * @param color The color of the line
     * @param start The start coordinates of the line
     * @param end The end coordinates of the line
     */
    public Line(final Color color, final Vertex start, final Vertex end){
        super(color);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor
     *
     * @param color The color of the line
     * @param start The start coordinates of the line
     * @param end The end coordinates of the line
     */
    public Line(final Color color, final Entry<Double, Double> start, final Entry<Double, Double> end){
        this(color, new Vertex(start), new Vertex(end));
    }

    /**
     * Gets the start coordinate
     *
     * @return {@link #start}
     */
    public Vertex getStart() {
        return start;
    }

    /**
     * Sets the start coordinate
     *
     * @param start The start coordinate
     */
    public void setStart(final Vertex start) {
        this.start = start;
    }

    /**
     * Gets the end coordinate
     *
     * @return {@link #end}
     */
    public Vertex getEnd() {
        return end;
    }

    /**
     * Sets the end coordinate
     *
     * @param end The end coordinate
     */
    public void setEnd(final Vertex end) {
        this.end = end;
    }

    /**
     * Renders the line
     */
    @Override
    public void render() {
        draw();
    }

    /**
     * Draws the shape onto the screen
     */
    @Override
    public void draw(){
        GL11.glBegin(GL11.GL_POINTS);
        GL11.glColor4d(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        drawLineByCoordinates(start.getKey(), start.getValue(), end.getKey(), end.getValue());
        GL11.glEnd();
    }

    /**
     * Static method to draw a line
     * @param color color of line
     * @param start start of line
     * @param end end of line
     */
    public static void drawLine(final Color color, final Entry<Double, Double> start, final Entry<Double, Double> end){
        new Line(color, start, end).draw();
    }

    /**
     * Draws the line onto the screen
     */
    private void drawLineByCoordinates(final double x0, final double y0, final double x1, final double y1){
        if(Math.abs(x1 - x0) > Math.abs(y1 - y0)){
            if(x0 > x1){
                drawLineByX(x1, y1, x0, y0);
            } else {
                drawLineByX(x0, y0, x1, y1);
            }
        } else {
            if(y0 > y1){
                drawLineByY(x1, y1, x0, y0);
            } else {
                drawLineByY(x0, y0, x1, y1);
            }
        }
    }

    /**
     * Draws the line onto the screen by incrementing the x-axis
     */
    private void drawLineByX(final double x0, final double y0, final double x1, final double y1){
        final double dx = (x1 - x0);
        final double dy = Math.abs(y1 - y0);
        final double yi = ((y1 - y0) < 0) ? -1.0 : 1.0;

        double d = 2.0 * dy - dx;
        for(double x = x0, y = y0; x <= x1; ++x){
            GL11.glVertex2d(x, y);
            if(d > 0.0){
                y += yi;
                d -= (2.0 * dx);
            }
            d += (2.0 * dy);
        }
    }

    /**
     * Draws the line onto the screen by incrementing the y-axis
     */
    private void drawLineByY(final double x0, final double y0, final double x1, final double y1){
        final double dy = (y1 - y0);
        final double dx = Math.abs(x1 - x0);
        final double xi = ((x1 - x0) < 0) ? -1.0 : 1.0;

        double d = 2.0 * dx - dy;
        for(double y = y0, x = x0; y <= y1; ++y){
            GL11.glVertex2d(x, y);
            if(d > 0.0){
                x += xi;
                d -= (2.0 * dy);
            }
            d += (2.0 * dx);
        }
    }

    /**
     * Overloaded {@link Object#toString()} method.
     *
     * @return Returns a string representation of the line
     */
    @Override
    public String toString(){
        return getClass().getName() + ":\n" +
                "\tStart: [" + start.getKey() + "," + start.getValue() + "]\n" +
                "\tEnd: [" + end.getKey() + "," + end.getValue() + "]\n";


    }

    /**
     * Overloaded {@link Object#equals(Object)} method.
     *
     * @param obj the reference object with which to compare.
     *
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(final Object obj){
        if(obj == null) { return false; }
        if(obj == this) { return true; }
        if(getClass() != obj.getClass()){
            return false;
        }

        final Line other = (Line) obj;

        return Objects.equals(start, other.start)
                && Objects.equals(end, other.end)
                && Objects.equals(color, other.color);
    }

    /**
     * Generates hash code
     *
     * @return hash code
     */
    @Override
    public int hashCode(){
        return Objects.hash(start, end, color);
    }

}
