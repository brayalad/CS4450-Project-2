package com.cpp.cs.cs4450.models.grid;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.Objects;

public class Vertex extends SimpleEntry<Double, Double> implements Entry<Double, Double>, Comparable<Vertex> {
    private double key;


    public Vertex(final Double key, final Double value) {
        super(key, value);
        this.key = key;
    }


    public Vertex(Entry<? extends Double, ? extends Double> entry) {
        this(entry.getKey(), entry.getValue());
    }

    public static Vertex of(final Double x, final Double y){
        return new Vertex(x, y);
    }

    @Override
    public Double getKey(){ return key; }

    public void setKey(final Double key){
        this.key = key;
    }

    public Double getX(){ return getKey(); }

    public void setX(final Double x){
        this.key = x;
    }

    public Double getY(){ return getValue(); }

    public void setY(final Double value){
        setValue(value);
    }


    @Override
    public int compareTo(final Vertex other) {
        if(this.getY() < other.getY()){
            return -1;
        } else if(this.getY().equals(other.getY())){
            return Double.compare(this.getX(), other.getX());
        } else {
            return 1;
        }
    }

    @Override
    public boolean equals(final Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(obj.getClass() != getClass()){
            return false;
        }

        final Vertex other = (Vertex) obj;

        return Objects.equals(this.getX(), other.getX()) && Objects.equals(this.getY(), other.getY());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString(){
        return "[" + getX() + ", " + getY() + "]";
    }

}
