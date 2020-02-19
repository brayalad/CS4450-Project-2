/***************************************************************
 * file: Transformation.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: Interface that allows it to transform a Transformable object.
 *
 ****************************************************************/

package com.cpp.cs.cs4450.models.transformations;

import com.cpp.cs.cs4450.graphics.Transformable;
import com.cpp.cs.cs4450.models.grid.Vertex;

import java.util.List;

/**
 * Interface that allows it to transform a Transformable object.
 */
public interface Transformation {

    /**
     * Transforms the transformable
     *
     * @param transformable object to transform
     */
    void transform(Transformable transformable);

    /**
     * Transforms the vertices
     *
     * @param vertices vertices to transform
     */
    void transform(List<Vertex> vertices);

}
