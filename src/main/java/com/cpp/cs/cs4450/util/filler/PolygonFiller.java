package com.cpp.cs.cs4450.util.filler;

import com.cpp.cs.cs4450.graphics.Fillable;
import com.cpp.cs.cs4450.models.grid.Vertex;
import com.cpp.cs.cs4450.models.shapes.Line;
import com.cpp.cs.cs4450.util.common.CommonUtils;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public final class PolygonFiller {
    private static final Comparator<EdgeBucket> EDGE_COMPARATOR = new EdgeComparator();

    private PolygonFiller(){}

    public static void fill(final Fillable fillable){
        fill(fillable.getVertices(), fillable.getColor());
    }

    public static void fill(final List<Vertex> vertices, final Color color){
        final Set<EdgeBucket> edgeBuckets = new HashSet<>();

        final int n = vertices.size();
        for(int i = 0; i < n; ++i){
            EdgeBucket edgeBucket;
            if(i == n - 1){
                edgeBucket = new EdgeBucket(vertices.get(i), vertices.get(0));
            } else {
                edgeBucket = new EdgeBucket(vertices.get(i), vertices.get(i + 1));
            }
            edgeBuckets.add(edgeBucket);
        }

        final SortedSet<EdgeBucket> globalEdgeBucketTable = new TreeSet<>();
        for(final EdgeBucket edgeBucket : edgeBuckets){
            if(!CommonUtils.isInfinity(edgeBucket.inverseSlope)){
                globalEdgeBucketTable.add(edgeBucket);
            }
        }

        double scanline = globalEdgeBucketTable.first().yMin;
        final List<EdgeBucket> activeEdgeBucketTable = new ArrayList<>();
        do {
            final double currentScanline = scanline;

            final Iterator<EdgeBucket> globalEdgeTableIterator = globalEdgeBucketTable.iterator();
            while (globalEdgeTableIterator.hasNext()){
                final EdgeBucket globalEdgeBucket = globalEdgeTableIterator.next();
                if((int) globalEdgeBucket.yMin == (int) currentScanline){
                    activeEdgeBucketTable.add(globalEdgeBucket);
                    globalEdgeTableIterator.remove();
                }
            }

            Collections.sort(activeEdgeBucketTable);

            boolean parity = true;
            for(int i = 0; i < activeEdgeBucketTable.size() - 1; ++i){
                if(parity){
                    final Vertex start = Vertex.of(activeEdgeBucketTable.get(i).xVal, currentScanline);
                    final Vertex end = Vertex.of(activeEdgeBucketTable.get(i + 1).xVal, currentScanline);

                    Line.drawLine(color, start, end);
                }
                parity = !parity;
            }

            activeEdgeBucketTable.forEach(ae -> ae.xVal += ae.inverseSlope);
            activeEdgeBucketTable.removeIf(ae -> (int) ae.yMax == (int) currentScanline);

            ++scanline;
        } while(!activeEdgeBucketTable.isEmpty());
    }


    private static final class EdgeBucket implements Comparable<EdgeBucket> {
        private final double yMin;
        private final double yMax;
        private final double inverseSlope;
        private double xVal;

        private EdgeBucket(final Vertex v1, final Vertex v2){
            this(
                    CommonUtils.minVertex(v1, v2).getY(),
                    CommonUtils.maxVertex(v1, v2).getY(),
                    (1.0 / CommonUtils.computeSlope(v1, v2)),
                    CommonUtils.minVertex(v1, v2).getX()
            );
        }

        private EdgeBucket(final double yMin, final double yMax, final double inverseSlope, final double xVal) {
            this.yMin = yMin;
            this.yMax = yMax;
            this.inverseSlope = inverseSlope;
            this.xVal = xVal;
        }

        @Override
        public int compareTo(final EdgeBucket other) {
            return EDGE_COMPARATOR.compare(this, other);
        }

        @Override
        public boolean equals(final Object obj){
            if(obj == null) return false;
            if(obj == this) return true;
            if(obj.getClass() != this.getClass()){
                return false;
            }

            final EdgeBucket other = (EdgeBucket) obj;

            return Objects.equals(this.yMin, other.yMin) &&
                    Objects.equals(this.xVal, other.xVal) &&
                    Objects.equals(this.yMax, other.yMax) &&
                    Objects.equals(this.inverseSlope, other.inverseSlope);
        }

        @Override
        public String toString(){
            return "\nEdge:" +
                    "\n\tY-Min:\t" + yMin +
                    "\n\tY-Max:\t" + yMax +
                    "\n\tX-Val:\t" + xVal +
                    "\n\tI-Slope:\t" + inverseSlope;
        }

    }

    private static class EdgeComparator implements Comparator<EdgeBucket> {

        @Override
        public int compare(final EdgeBucket e1, final EdgeBucket e2) {
            if(e1.yMin < e2.yMin){
                return -1;
            } else if(e1.yMin == e2.yMin){
                if(e1.xVal < e2.xVal){
                    return -1;
                } else if(e1.xVal == e2.xVal){
                    return Double.compare(e1.yMax, e2.yMax);
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }
    }

}
