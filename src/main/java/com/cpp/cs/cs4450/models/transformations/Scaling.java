package com.cpp.cs.cs4450.models.transformations;

import com.cpp.cs.cs4450.graphics.Transformable;
import com.cpp.cs.cs4450.models.grid.Vertex;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.Objects;

public class Scaling extends AbstractTransformation implements Transformation{
    private final double sx;
    private final double sy;
    private final Entry<Double, Double> pivot;
    private final Translation translation;
    private final Translation recenter;


    public Scaling(final double sx, final double sy, final double px, final double py){
        this(sx, sy, new SimpleEntry<>(px, py));
    }

    public Scaling(final double sx, final double sy, final Entry<Double, Double> pivot) {
        this.sx = sx;
        this.sy = sy;
        this.pivot = pivot;
        this.translation = new Translation(pivot.getKey(), pivot.getValue());
        this.recenter = new Translation(-pivot.getKey(), - pivot.getValue());
    }

    @Override
    public void transform(final Transformable transformable){
        translation.transform(transformable);
        transform(transformable.getVertices());
        recenter.transform(transformable);
    }

    @Override
    protected void transform(final Vertex vertex){
        final double x = vertex.getX();
        final double y = vertex.getY();

        vertex.setX(x * sx);
        vertex.setY(y * sy);
    }

    @Override
    public boolean equals(final Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(getClass() != obj.getClass()){
            return false;
        }

        final Scaling other = (Scaling) obj;

        return Objects.equals(this.sx, other.sx) &&
                Objects.equals(this.sy, other.sy) &&
                Objects.equals(this.pivot, other.pivot);
    }

    @Override
    public String toString(){
        return "Scaling:\n" +
                "\tX:\t" + sx + "\n" +
                "\tY:\t" + sy + "\n" +
                "\tPivot:\t[" + pivot.getKey() + ", " + pivot.getValue() + "]\n";
    }

}
