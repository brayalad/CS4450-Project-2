package com.cpp.cs.cs4450.graphics;

import com.cpp.cs.cs4450.models.grid.Vertex;
import com.cpp.cs.cs4450.models.transformations.Transformation;

import java.util.Collection;
import java.util.List;

public interface Transformable {

    List<Vertex> getVertices();

    void transform();

    void transform(Collection<Transformation> transformations);

    void transform(Transformation transformation);

}
