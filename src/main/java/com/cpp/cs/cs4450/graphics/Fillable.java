/***************************************************************
 * file: Fillable.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: Interface that allows it to be filled
 *
 ****************************************************************/
package com.cpp.cs.cs4450.graphics;

import com.cpp.cs.cs4450.models.grid.Vertex;

import java.awt.Color;
import java.util.List;

/**
 * Interface that allows it to be filled
 */
public interface Fillable {

    /**
     * Getter for color
     *
     * @return color
     */
    Color getColor();

    /**
     * Getter for vertices
     *
     * @return vertices
     */
    List<Vertex> getVertices();

}
