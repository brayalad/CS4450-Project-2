package com.cpp.cs.cs4450.models.shapes;

import com.cpp.cs.cs4450.graphics.Fillable;
import com.cpp.cs.cs4450.graphics.Renderable;
import com.cpp.cs.cs4450.graphics.Transformable;
import com.cpp.cs.cs4450.models.grid.Vertex;
import com.cpp.cs.cs4450.models.transformations.Transformation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Polygon extends DisplayShape implements Transformable, Renderable, Fillable {
    private final List<Vertex> vertices;
    private final Set<Transformation> transformations;

    public Polygon(final Color color, final List<Vertex> vertices, final Set<Transformation> transformations) {
    //public Polygon(final Color color, final List<Vertex> vertices){
        super(color);
        this.vertices = vertices;
        this.transformations = transformations;
    }


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


    private void drawPolygon(){
        for(final Line line : getLines()){
            line.draw();
        }
    }

    public List<Line> getLines(){
        final List<Line> lines = new ArrayList<>();

        final LinkedList<Vertex> vertexList = new LinkedList<>(vertices);
        lines.add(new Line(this.color, vertexList.getLast(), vertexList.getFirst()));
        for(int i = 0; i < vertexList.size() - 1; ++i){
            lines.add(new Line(this.color, vertexList.get(i), vertexList.get(i + 1)));
        }

        return lines;
    }

    @Override
    public void transform(){
        transform(transformations);
    }

    @Override
    public void transform(final Collection<Transformation> transformations){
        transformations.forEach(this::transform);
    }

    @Override
    public void transform(final Transformation transformation){
        transformation.transform(this);
    }

    @Override
    public String toString(){
        return vertices.toString();
    }

}
