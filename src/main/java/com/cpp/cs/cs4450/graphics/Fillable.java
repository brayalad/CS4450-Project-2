package com.cpp.cs.cs4450.graphics;

import com.cpp.cs.cs4450.models.grid.Vertex;

import java.awt.Color;
import java.util.List;


public interface Fillable {

    Color getColor();

    List<Vertex> getVertices();

}
