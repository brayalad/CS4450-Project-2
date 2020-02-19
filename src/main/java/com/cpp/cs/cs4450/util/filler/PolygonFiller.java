/***************************************************************
 * file: PolygonFiller.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: Utility class that uses Scan-line fill algorithm to fill polygons
 *
 ****************************************************************/

package com.cpp.cs.cs4450.util.filler;

import com.cpp.cs.cs4450.graphics.Fillable;
import com.cpp.cs.cs4450.models.grid.Vertex;
import com.cpp.cs.cs4450.models.shapes.Line;
import com.cpp.cs.cs4450.util.common.Utils;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Utility class that uses Scan-line fill algorithm to fill polygons
 */
public final class PolygonFiller {

    /**
     * private Constrictor
     */
    private PolygonFiller(){}

    /**
     * Fills the object
     *
     * @param fillable fillable object
     */
    public static void fill(final Fillable fillable){
        fill(fillable.getVertices(), fillable.getColor());
    }

    /**
     * Fills the fillable
     *
     * @param vertices map of vertices
     * @param color color of object
     */
    public static void fill(final Map<Vertex, List<Vertex>> vertices, final Color color){
        final Set<Edge> edges = new HashSet<>();
        for(final Vertex source : vertices.keySet()){
            for(final Vertex destination : vertices.get(source)){
                edges.add(new Edge(source, destination));
            }
        }

        fill(edges, color);
    }

    /**
     * Fills the fillable
     *
     * @param vertices list of vertices
     * @param color color of object
     */
    public static void fill(final List<Vertex> vertices, final Color color){
        final Set<Edge> edges = new HashSet<>();

        final int n = vertices.size();
        for(int i = 0; i < n; ++i){
            Edge edge;
            if(i == n - 1){
                edge = new Edge(vertices.get(i), vertices.get(0));
            } else {
                edge = new Edge(vertices.get(i), vertices.get(i + 1));
            }
            edges.add(edge);
        }

        fill(edges, color);
    }

    /**
     * Fills the fillable
     *
     * @param edges set of edges
     * @param color color of object
     */
    private static void fill(final Set<Edge> edges, final Color color){
        final SortedSet<Edge> globalEdgeTable = new TreeSet<>();
        for(final Edge edge : edges){
            if(!Utils.isInfinity(edge.inverseSlope)){
                globalEdgeTable.add(edge);
            }
        }

        double scanline = globalEdgeTable.first().yMin;
        final List<Edge> activeEdgeTable = new ArrayList<>();
        do {
            final double currentScanline = scanline;

            final Iterator<Edge> globalEdgeTableIterator = globalEdgeTable.iterator();
            while (globalEdgeTableIterator.hasNext()){
                final Edge globalEdge = globalEdgeTableIterator.next();
                if((int) globalEdge.yMin == (int) currentScanline){
                    activeEdgeTable.add(globalEdge);
                    globalEdgeTableIterator.remove();
                }
            }

            if(!Utils.isInOrder(activeEdgeTable)){
                Collections.sort(activeEdgeTable);
            }

            boolean parity = true;
            for(int i = 0; i < activeEdgeTable.size() - 1; ++i){
                if(parity){
                    final Vertex start = Vertex.of(activeEdgeTable.get(i).xVal, currentScanline);
                    final Vertex end = Vertex.of(activeEdgeTable.get(i + 1).xVal, currentScanline);

                    Line.drawLine(color, start, end);
                }
                parity = !parity;
            }

            activeEdgeTable.forEach(ae -> ae.xVal += ae.inverseSlope);
            activeEdgeTable.removeIf(ae -> (int) ae.yMax == (int) currentScanline);

            ++scanline;
        } while(!activeEdgeTable.isEmpty());
    }

    /**
     * Class the represents edge between to vertices
     */
    private static final class Edge implements Comparable<Edge> {

        /**
         * min y value
         */
        private final double yMin;

        /**
         * max y value
         */
        private final double yMax;

        /**
         * Inverse of the slope
         */
        private final double inverseSlope;

        /**
         * x value of min y
         */
        private double xVal;

        /**
         * Constructor
         *
         * @param v1 start vertex
         * @param v2 end vertex
         */
        private Edge(final Vertex v1, final Vertex v2){
            this(
                    Utils.minVertex(v1, v2).getY(),
                    Utils.maxVertex(v1, v2).getY(),
                    (1.0 / Utils.computeSlope(v1, v2)),
                    Utils.minVertex(v1, v2).getX()
            );
        }

        /**
         * Constructor
         *
         * @param yMin min y value
         * @param yMax max y value
         * @param inverseSlope inverse of slope
         * @param xVal x val of y min
         */
        private Edge(final double yMin, final double yMax, final double inverseSlope, final double xVal) {
            this.yMin = yMin;
            this.yMax = yMax;
            this.inverseSlope = inverseSlope;
            this.xVal = xVal;
        }

        /**
         * Compares object to edge
         *
         * @param other other edge
         *
         * @return if equal or not
         */
        @Override
        public int compareTo(final Edge other) {
            if(this.yMin < other.yMin){
                return -1;
            } else if(this.yMin == other.yMin){
                if(this.xVal < other.xVal){
                    return -1;
                } else if(this.xVal == other.xVal){
                    return Double.compare(this.yMax, other.yMax);
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }

        /**
         * Checks if equal to object
         *
         * @param obj object to check
         *
         * @return true if equal, false otherwise
         */
        @Override
        public boolean equals(final Object obj){
            if(obj == null) return false;
            if(obj == this) return true;
            if(obj.getClass() != this.getClass()){
                return false;
            }

            final Edge other = (Edge) obj;

            return Objects.equals(this.yMin, other.yMin) &&
                    Objects.equals(this.xVal, other.xVal) &&
                    Objects.equals(this.yMax, other.yMax) &&
                    Objects.equals(this.inverseSlope, other.inverseSlope);
        }

        /**
         * String representation
         *
         * @return String representation
         */
        @Override
        public String toString(){
            return "\nEdge:" +
                    "\n\tY-Min:\t" + yMin +
                    "\n\tY-Max:\t" + yMax +
                    "\n\tX-Val:\t" + xVal +
                    "\n\tI-Slope:\t" + inverseSlope;
        }

        /**
         * Hash Code
         *
         * @return hash code
         */
        @Override
        public int hashCode(){
            return Objects.hash(yMin, xVal, yMax, inverseSlope);
        }

    }

}
