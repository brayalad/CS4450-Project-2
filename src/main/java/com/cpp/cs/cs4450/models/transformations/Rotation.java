package com.cpp.cs.cs4450.models.transformations;

import com.cpp.cs.cs4450.models.grid.Vertex;
import com.cpp.cs.cs4450.util.common.CommonUtils;
import com.cpp.cs.cs4450.util.math.MathUtils;

import java.awt.geom.AffineTransform;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Objects;

public class Rotation extends AbstractTransformation implements Transformation {
    private final double rotationAngle;
    private final Entry<Double, Double> pivot;
    private final AffineTransform affineTransform;
    private final double[][] matrix;

    public Rotation(final double angle, final double px, final double py){
        this(angle, new SimpleEntry<>(px,py));
    }

    public Rotation(final double rotationAngle, final Entry<Double, Double> pivot) {
        this.rotationAngle = rotationAngle;
        this.pivot = pivot;
        this.affineTransform = AffineTransform.getRotateInstance(Math.toRadians(rotationAngle), pivot.getKey(), pivot.getValue());
        this.matrix = new double[][]{
                {Math.cos(rotationAngle), -Math.sin(rotationAngle)},
                {Math.sin(rotationAngle), Math.cos(rotationAngle)}
        };
    }

    public double getRotationAngle() {
        return rotationAngle;
    }

    public Entry<Double, Double> getPivot() {
        return pivot;
    }

    @Override
    protected void transform(final Vertex vertex){
        final double[] points = CommonUtils.pairToArray(vertex);
        affineTransform.transform(points, 0, points, 0, 1);

        final Entry<Double, Double> rotated = CommonUtils.arrayToPair(points);

        vertex.setX(rotated.getKey());
        vertex.setY(rotated.getValue());
        }

    @Override
    public boolean equals(final Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(getClass() != obj.getClass()){
            return false;
        }

        final Rotation other = (Rotation) obj;

        return Objects.equals(this.rotationAngle, other.rotationAngle) &&
                Objects.equals(this.pivot, other.pivot);
    }

    @Override
    public String toString(){
        return "Scaling:\n" +
                "\tAngle:\t" + rotationAngle + "\n" +
                "\tPivot:\t[" + pivot.getKey() + ", " + pivot.getValue() + "]\n";
    }

    private double calculateRotatedX(final Vertex vertex){
        return (pivot.getKey() + (vertex.getX() - pivot.getKey()) * Math.cos(rotationAngle) - (vertex.getY() - pivot.getValue()) * Math.sin(rotationAngle));
    }

    private double calculateRotatedY(final Vertex vertex){
        return (pivot.getValue() + (vertex.getX() - pivot.getKey()) * Math.sin(rotationAngle) + (vertex.getY() - pivot.getValue()) * Math.cos(rotationAngle));
    }

}
