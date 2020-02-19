/***************************************************************
 * file: Utils.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: Utility class to provide commonly used functions
 *
 ****************************************************************/

package com.cpp.cs.cs4450.util.common;


import com.cpp.cs.cs4450.models.grid.Vertex;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Utility class to provide commonly used functions
 */
public final class Utils {
    /**
     * Pair string delimiter
     */
    private static final String DEFAULT_PAIR_DELIMITER = " ";

    /**
     * String negative number prefix
     */
    private static final String NEGATIVE_NUMBER_PREFIX = "-";

    /**
     * Error message for Error that sometimes happens when pasting in negative numbers in txt file
     */
    private static final String NUMBER_FORMAT_ERROR_MESSAGE = "An Number Format Error was thrown with message: [%s].\n" +
            "This sometimes happens when the coordinates are copied and pasted into the Coordinates.txt file\n" +
            "Please type in the negative coordinates by hand since the '" + NEGATIVE_NUMBER_PREFIX + "' symbol is sometimes not recognized when pasted.";

    /**
     * private constructor
     */
    private Utils(){}


    /**
     * Checks if collection is in order
     *
     * @param collection collection to check
     * @param <T> comparable type
     *
     * @return true if in order, false otherwise
     */
    public static <T extends Comparable<T>> boolean isInOrder(final Collection<T> collection){
        return (isInOrder(collection, Comparable::compareTo));
    }

    /**
     * Checks if collection is in order
     *
     * @param collection collection to check
     * @param comparator how to compare
     * @param <T> type
     *
     * @return true if in order, false otherwise
     */
    public static <T> boolean isInOrder(final Collection<T> collection, final Comparator<T> comparator){
        if(collection.isEmpty() || collection.size() <= 1){
            return true;
        }

        final Iterator<T> iterator = collection.iterator();
        T current, previous = iterator.next();
        while(iterator.hasNext()){
            current = iterator.next();
            if(comparator.compare(previous, current) > 0){
                return false;
            }
        }

        return true;
    }

    /**
     * parse string to pair
     *
     * @param s pair string
     *
     * @return parsed pair
     */
    public static Entry<Double, Double> parsePairToDouble(final String s){
        return parsePairToDouble(s, DEFAULT_PAIR_DELIMITER);
    }

    /**
     * Parses String to pair
     *
     * @param s pair string
     * @param regex delimiter
     *
     * @return parsed pair
     */
    public static Entry<Double, Double> parsePairToDouble(final String s, final String regex){
        try {
            final Double[] pair = Arrays.stream(s.split(regex))
                    .map(Double::parseDouble)
                    .toArray(Double[]::new);

            return new SimpleEntry<>(pair[0], pair[1]);
        } catch (NumberFormatException e){
            throw new RuntimeException(String.format(NUMBER_FORMAT_ERROR_MESSAGE, e.getLocalizedMessage()));
        }
    }

    /**
     * Returns max vertex
     *
     * @param v1 first vertex
     * @param v2 second vertex
     *
     * @return max vertex
     */
    public static Vertex maxVertex(final Vertex v1, final Vertex v2){
        return (v1.compareTo(v2) > 0) ? v1 : v2;
    }

    /**
     * Returns min vertex
     *
     * @param v1 first vertex
     * @param v2 second vertex
     *
     * @return min vertex
     */
    public static Vertex minVertex(final Vertex v1, final Vertex v2){
        return (v1.compareTo(v2) <= 0) ? v1 : v2;
    }

    /**
     * Computes slope
     *
     * @param e1 first point
     * @param e2 second point
     *
     * @return slope
     */
    public static double computeSlope(final Entry<Double, Double> e1, final Entry<Double, Double> e2){
        return computeSlope(e1.getKey(), e1.getValue(), e2.getKey(), e2.getValue());
    }

    /**
     * Computes Slope
     *
     * @param x0 first point x
     * @param y0 first point y
     * @param x1 second point x
     * @param y1 second point y
     *
     * @return slope
     */
    public static double computeSlope(final double x0, final double y0, final double x1, final double y1){
        return (y1 - y0) / (x1 - x0);
    }

    /**
     * Checks if number is infinity
     *
     * @param n number to check
     *
     * @return true if is infinity, false otherwise
     */
    public static boolean isInfinity(final double n){
        return n == Double.POSITIVE_INFINITY || n == Double.NEGATIVE_INFINITY;
    }

}
