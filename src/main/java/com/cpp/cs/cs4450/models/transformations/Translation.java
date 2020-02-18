package com.cpp.cs.cs4450.models.transformations;

import com.cpp.cs.cs4450.models.grid.Vertex;
import com.cpp.cs.cs4450.util.math.MathUtils;

import java.util.Map.Entry;
import java.util.Objects;

public class Translation extends AbstractTransformation implements Transformation {
    private final double dx;
    private final double dy;

    public Translation(final double dx, final double dy) {
        this.dx = dx;
        this.dy = dy;
    }


    public Translation(final Entry<Double, Double> entry){
        this(entry.getKey(), entry.getValue());
    }

    @Override
    protected void transform(final Vertex vertex){
        final double x = vertex.getX();
        final double y = vertex.getY();

        vertex.setX(x + dx);
        vertex.setY(y + dy);
    }

    @Override
    public boolean equals(final Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(getClass() != obj.getClass()){
            return false;
        }

        final Translation other = (Translation) obj;

        return Objects.equals(this.dx, other.dx) &&
                Objects.equals(this.dy, other.dy);
    }

    @Override
    public String toString(){
        return "Scaling:\n" +
                "\tX:\t" + dx + "\n" +
                "\tY:\t" + dy + "\n";
    }

}
