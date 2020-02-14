package com.cpp.cs.cs4450.util.factory;

import com.cpp.cs.cs4450.models.grid.Vertex;
import com.cpp.cs.cs4450.models.shapes.Polygon;
import com.cpp.cs.cs4450.util.common.CommonUtils;
import com.cpp.cs.cs4450.util.io.PolygonCoordinateFileParser.ParsedFilePolygon;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class PolygonFactory {
    private static final String PAIR_DELIMITER = " ";


    private PolygonFactory(){}

    public static List<Polygon> createPolygons(final Collection<ParsedFilePolygon> filePolygons){
        return filePolygons.stream()
                .map((pfp) -> {
                    final Float[] rgb = Arrays.stream(pfp.getColor().split(PAIR_DELIMITER))
                                        .map(Float::parseFloat)
                                        .toArray(Float[]::new);
                    return new Polygon(
                            new Color(rgb[0], rgb[1], rgb[2]),
                            pfp.getVertices().stream()
                                    .map(CommonUtils::parsePairToDouble)
                                    .map(Vertex::new)
                                    .collect(Collectors.toList()),
                            TransformationFactory.createTransformations(pfp.getTransformations())
                    );
                }).collect(Collectors.toList());
    }

    /*
    public static List<Polygon> create(final Collection<ParsedFilePolygon> filePolygonCollection){
        return filePolygonCollection.stream().map(PolygonFactory::create).collect(Collectors.toList());
    }

    public static Polygon create(final ParsedFilePolygon filePolygon){
        return new Polygon(
                color(filePolygon.getColor()),
                filePolygon.getVertices().stream()
                        .map(CommonUtils::parsePairToDouble)
                        .map(Vertex::new)
                        .collect(Collectors.toList()),
                TransformationFactory.createTransformations(filePolygon.getTransformations())
        );
    }

    private static Color color(final String c){
        final Float[] rgb = Arrays.stream(c.split(PAIR_DELIMITER))
                            .map(Float::parseFloat)
                            .toArray(Float[]::new);

        final float red = rgb[0];
        final float green = rgb[1];
        final float blue = rgb[2];

        return new Color(red, green, blue);
    }

     */



}
