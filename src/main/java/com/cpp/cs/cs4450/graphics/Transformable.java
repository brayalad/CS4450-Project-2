/***************************************************************
 * file: Transformable.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: Interface that allows it to be transformed
 *
 ****************************************************************/

package com.cpp.cs.cs4450.graphics;

import com.cpp.cs.cs4450.models.grid.Vertex;
import com.cpp.cs.cs4450.models.transformations.Transformation;

import java.util.Collection;
import java.util.List;

/**
 * Interface that allows it to be transformed
 */
public interface Transformable {

    /**
     * Getter for vertices
     *
     * @return list of vertices
     */
    List<Vertex> getVertices();

    /**
     * Transforms the Transformable
     */
    void transform();

    /**
     * Transforms the Transformable
     *
     * @param transformations collections of transformations to do
     */
    void transform(Collection<Transformation> transformations);

    /**
     * Transforms the Transformable with Transformation
     *
     * @param transformation Transformation to use
     */
    void transform(Transformation transformation);

}
