/***************************************************************
 * file: PolygonFactory.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: Factory utility class to create polygons
 *
 ****************************************************************/

package com.cpp.cs.cs4450.util.factory;

import com.cpp.cs.cs4450.models.grid.Vertex;
import com.cpp.cs.cs4450.models.shapes.Polygon;
import com.cpp.cs.cs4450.util.common.Utils;
import com.cpp.cs.cs4450.util.io.PolygonCoordinateFileParser.ParsedFilePolygon;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Factory utility class to create polygons
 */
public final class PolygonFactory {

    /**
     * Pair string delimiter
     */
    private static final String PAIR_DELIMITER = " ";

    /**
     * Set of supported colors.
     */
    public static final Set<Color> SUPPORTED_COLORS = Collections.unmodifiableSet(
            Stream.of(
                    Color.BLUE,
                    Color.GREEN,
                    Color.CYAN,
                    Color.GRAY,
                    Color.LIGHT_GRAY,
                    Color.MAGENTA,
                    Color.ORANGE,
                    Color.PINK,
                    Color.RED,
                    Color.WHITE,
                    Color.YELLOW
            ).collect(Collectors.toSet())
    );

    /**
     * private constructor
     */
    private PolygonFactory(){}

    /**
     * Creates polygons
     *
     * @param filePolygons parsed polygons from file
     *
     * @return polygons
     */
    public static List<Polygon> createPolygons(final Collection<ParsedFilePolygon> filePolygons){
        return filePolygons.stream()
                .map((pfp) -> {
                    final Integer[] rgb = Arrays.stream(pfp.getColor().split(PAIR_DELIMITER))
                                        .map(Float::parseFloat)
                                        .map(Math::round)
                                        .toArray(Integer[]::new);
                    return new Polygon(
                            new Color(rgb[0], rgb[1], rgb[2]),
                            pfp.getVertices().stream()
                                    .map(Utils::parsePairToDouble)
                                    .map(Vertex::new)
                                    .collect(Collectors.toList()),
                            TransformationFactory.createTransformations(pfp.getTransformations())
                    );
                }).collect(Collectors.toList());
    }

    /**
     * Transforms list of vertices to map
     *
     * @param vertices vertices list
     *
     * @return vertices map
     */
    public static Map<Vertex, List<Vertex>> vertexListToMap(final List<Vertex> vertices){
        final Map<Vertex, List<Vertex>> map = new LinkedHashMap<>();

        final int n = vertices.size();
        for(int i = 0; i < n; ++i){
            final Vertex start = vertices.get(i);
            final Vertex end = (i == n - 1) ? vertices.get(0) : vertices.get(i + 1);

            map.put(start, Stream.of(end).collect(Collectors.toList()));
        }

        return map;
    }

}
