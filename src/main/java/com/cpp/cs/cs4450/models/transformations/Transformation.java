package com.cpp.cs.cs4450.models.transformations;

import com.cpp.cs.cs4450.graphics.Transformable;
import com.cpp.cs.cs4450.models.grid.Vertex;

import java.util.List;

public interface Transformation {

    void transform(Transformable transformable);

    void transform(List<Vertex> vertices);

    double[][] getTransformationMatrix();

}
