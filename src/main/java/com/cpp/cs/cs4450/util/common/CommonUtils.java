package com.cpp.cs.cs4450.util.common;


import com.cpp.cs.cs4450.models.grid.Vertex;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map.Entry;


public final class CommonUtils {
    private static final String DEFAULT_PAIR_DELIMITER = " ";
    private static final String NEGATIVE_NUMBER_PREFIX = "-";


    private CommonUtils(){}

    public static Entry<Double, Double> parsePairToDouble(final String s){
        return parsePairToDouble(s, DEFAULT_PAIR_DELIMITER);
    }

    public static Entry<Double, Double> parsePairToDouble(final String s, final String regex){
        final Double[] pair = Arrays.stream(s.split(regex))
                                .map(Double::parseDouble)
                                .toArray(Double[]::new);

        return new SimpleEntry<>(pair[0], pair[1]);
    }

    public static double[] pairToArray(final Entry<Double, Double> pair){
        return new double[] { pair.getKey(), pair.getValue() };
    }

    public static Entry<Double, Double> arrayToPair(final double[] a){
        return new SimpleEntry<>(a[0], a[1]);
    }

    public static Vertex maxVertex(final Vertex v1, final Vertex v2){
        return (v1.compareTo(v2) > 0) ? v1 : v2;
    }

    public static Vertex minVertex(final Vertex v1, final Vertex v2){
        return (v1.compareTo(v2) <= 0) ? v1 : v2;
    }

    public static double computeSlope(final Entry<Double, Double> e1, final Entry<Double, Double> e2){
        return computeSlope(e1.getKey(), e1.getValue(), e2.getKey(), e2.getValue());
    }

    public static double computeSlope(final double x0, final double y0, final double x1, final double y1){
        return (y1 - y0) / (x1 - x0);
    }

    public static boolean isNullOrEmpty(final CharSequence charSequence){
        return isNull(charSequence) || (charSequence.length() == 0);
    }

    public static boolean isNullOrEmpty(final Collection<?> collection){
        return isNull(collection) || (collection.isEmpty());
    }

    public static boolean isNull(final Object obj){
        return obj == null;
    }

    public static boolean isInfinity(final double n){
        return n == Double.POSITIVE_INFINITY || n == Double.NEGATIVE_INFINITY;
    }

}
