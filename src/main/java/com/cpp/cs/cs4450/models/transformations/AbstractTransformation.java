/***************************************************************
 * file: AbstractTransformation.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: Skeleton implementation of a Transformation
 *
 ****************************************************************/
package com.cpp.cs.cs4450.models.transformations;

import com.cpp.cs.cs4450.graphics.Transformable;
import com.cpp.cs.cs4450.models.grid.Vertex;

import java.util.List;

/**
 * Skeleton implementation of a Transformation
 */
public abstract class AbstractTransformation implements Transformation {

    /**
     * Transforms the transformable
     *
     * @param transformable object to transform
     */
    @Override
    public void transform(final Transformable transformable) {
        transform(transformable.getVertices());
    }


    /**
     * Transforms the vertices
     *
     * @param vertices vertices to transform
     */
    @Override
    public void transform(final List<Vertex> vertices) {
        vertices.forEach(this::transform);
    }

    /**
     * Transforms a vertex
     *
     * @param vertex vertex to transform
     */
    protected abstract void transform(final Vertex vertex);


}
