package com.cpp.cs.cs4450.models.transformations;

import com.cpp.cs.cs4450.graphics.Transformable;
import com.cpp.cs.cs4450.models.grid.Vertex;

import java.util.List;

public abstract class AbstractTransformation implements Transformation {

    @Override
    public void transform(final Transformable transformable) {
        transform(transformable.getVertices());
    }

    @Override
    public void transform(final List<Vertex> vertices) {
        vertices.forEach(this::transform);
    }

    protected abstract void transform(final Vertex vertex);

}
