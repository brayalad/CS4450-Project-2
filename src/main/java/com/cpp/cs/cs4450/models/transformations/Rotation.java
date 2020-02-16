package com.cpp.cs.cs4450.models.transformations;

import com.cpp.cs.cs4450.graphics.Transformable;
import com.cpp.cs.cs4450.models.grid.Vertex;
import com.cpp.cs.cs4450.util.common.CommonUtils;
import com.cpp.cs.cs4450.util.math.MathUtils;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.Objects;

public class Rotation extends AbstractTransformation implements Transformation {
    private final double theta;
    private final Entry<Double, Double> pivot;
    private final Translation translation;
    private final Translation recenter;
    private final double[][] transformationMatrix;

    public Rotation(final double angle, final double px, final double py){
        this(angle, new SimpleEntry<>(px,py));
    }

    public Rotation(final double theta, final Entry<Double, Double> pivot) {
        this.theta = Math.toRadians(theta);
        this.pivot = pivot;
        this.translation = new Translation(pivot.getKey(), pivot.getValue());
        this.recenter = new Translation(-pivot.getKey(), - pivot.getValue());
        this.transformationMatrix = new double[][] {
                { Math.cos(this.theta), -Math.sin(this.theta) },
                { Math.sin(this.theta), Math.cos(this.theta) }
        };
    }

    public double getTheta() {
        return theta;
    }

    public Entry<Double, Double> getPivot() {
        return pivot;
    }


    @Override
    public void transform(final Transformable transformable){
        translation.transform(transformable);
        transform(transformable.getVertices());
        recenter.transform(transformable);
    }

    @Override
    public double[][] getTransformationMatrix() {
        return transformationMatrix;
    }

    @Override
    protected void transform(final Vertex vertex){
        final double x = vertex.getX();
        final double y = vertex.getY();

        vertex.setX(x * Math.cos(theta) - y * Math.sin(theta));
        vertex.setY(x * Math.sin(theta) + y * Math.cos(theta));
        }

    @Override
    public boolean equals(final Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(getClass() != obj.getClass()){
            return false;
        }

        final Rotation other = (Rotation) obj;

        return Objects.equals(this.theta, other.theta) &&
                Objects.equals(this.pivot, other.pivot);
    }

    @Override
    public String toString(){
        return "Scaling:\n" +
                "\tAngle:\t" + theta + "\n" +
                "\tPivot:\t[" + pivot.getKey() + ", " + pivot.getValue() + "]\n";
    }

    private double calculateRotatedX(final Vertex vertex){
        return (pivot.getKey() + (vertex.getX() - pivot.getKey()) * Math.cos(theta) - (vertex.getY() - pivot.getValue()) * Math.sin(theta));
    }

    private double calculateRotatedY(final Vertex vertex){
        return (pivot.getValue() + (vertex.getX() - pivot.getKey()) * Math.sin(theta) + (vertex.getY() - pivot.getValue()) * Math.cos(theta));
    }

}
