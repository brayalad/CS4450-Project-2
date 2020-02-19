/***************************************************************
 * file: PolygonCoordinateFileParser.java
 * author: Bryan Ayala
 * class: CS 4450 - Computer Graphics
 *
 * assignment: Program 2
 * date last modified: 02/19/2020
 *
 * purpose: Utility class to parse provided coordinates.txt file
 *
 ****************************************************************/

package com.cpp.cs.cs4450.util.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * Utility class to parse provided coordinates.txt file
 */
public final class PolygonCoordinateFileParser {

    /**
     * polygon string flag
     */
    public static final String POLYGON_FLAG = "P";

    /**
     * transformation string flag
     */
    public static final String TRANSFORMATION_FLAG = "T";

    /**
     * new line string regex
     */
    private static final String NEW_LINE_REG_EX = "\n";

    /**
     * String join delimiter
     */
    private static final String STRING_JOIN_DELIMITER = " ";


    /**
     * private constructor
     */
    private PolygonCoordinateFileParser(){}

    /**
     * Parses coordinates.txt file
     *
     * @param file file to read
     *
     * @return parsed polygons
     *
     * @throws IOException if file does not exist
     */
    public static List<ParsedFilePolygon> parse(final String file) throws IOException {
        return Arrays.stream(new String(Files.readAllBytes(Paths.get(file))).split(POLYGON_FLAG))
                .filter(s -> !s.isEmpty())
                .map(String::trim)
                .map((p) -> {
                    final List<String> str = Arrays.stream(p.split(TRANSFORMATION_FLAG))
                            .filter(e -> !e.isEmpty())
                            .map(String::trim)
                            .collect(Collectors.toList());

                    final List<String> polygon = Arrays.stream(str.get(0).split(NEW_LINE_REG_EX))
                            .collect(Collectors.toList());

                    return ParsedFilePolygon.builder()
                            .withColor(polygon.remove(0))
                            .withVertices(polygon)
                            .withTransformations(
                                    Arrays.stream(str.get(1).split(NEW_LINE_REG_EX))
                                    .map((t) -> {
                                        final StringTokenizer stringTokenizer = new StringTokenizer(t);

                                        final LinkedList<String> transformation = new LinkedList<>();
                                        while(stringTokenizer.hasMoreElements()){
                                            transformation.add(stringTokenizer.nextToken());
                                        }

                                        final String key = transformation.removeFirst();
                                        final String value = String.join(STRING_JOIN_DELIMITER, transformation);

                                        return new SimpleEntry<>(key, value);
                                    }).collect(Collectors.toSet())
                            ).build();
                }).collect(Collectors.toList());
    }


    /**
     * Parsed polygon from a file
     */
    public static final class ParsedFilePolygon {
        /**
         * Color of polygon
         */
        private final String color;

        /**
         * vertices strings for polygon
         */
        private final List<String> vertices;

        /**
         * Transformations
         */
        private final Set<Entry<String, String>> transformations;


        /**
         * Constructor
         *
         * @param color color of polygon
         * @param vertices list of vertices
         * @param transformations transformations
         */
        private ParsedFilePolygon(final String color, final List<String> vertices, final Set<Entry<String, String>> transformations) {
            this.color = color;
            this.vertices = vertices;
            this.transformations = transformations;
        }

        /**
         * Getter for color
         *
         * @return color
         */
        public String getColor() {
            return color;
        }

        /**
         * Getter for vertices
         *
         * @return vertices
         */
        public List<String> getVertices() {
            return vertices;
        }

        /**
         * Getter for transformations
         *
         * @return transformation
         */
        public Set<Entry<String, String>> getTransformations() {
            return transformations;
        }

        /**
         * Returns a builder
         *
         * @return new builder
         */
        private static Builder builder(){
            return new Builder();
        }

        /**
         * String representation
         *
         * @return String representation
         */
        @Override
        public String toString(){
            return "Parsed File Polygon:\n" +
                    "\tColor:\t" + color + "\n" +
                    "\tVertices:\t" + vertices.toString() + "\n" +
                    "\tTransformations:\t" + transformations.toString() + "\n";
        }

        /**
         * Builder for parsed polygon
         */
        private static final class Builder {
            /**
             * Color of polygon
             */
            private String color;

            /**
             * vertices strings for polygon
             */
            private List<String> vertices;

            /**
             * Transformations
             */
            private Set<Entry<String, String>> transformations;

            /**
             * Constructor
             */
            private Builder(){}

            /**
             * Sets color
             *
             * @param color color value
             *
             * @return this builder
             */
            private Builder withColor(String color){
                setColor(color);
                return this;
            }

            /**
             * Sets Color
             *
             * @param color color value
             */
            private void setColor(String color) {
                this.color = color;
            }

            /**
             * Sets value
             *
             * @param vertices vertices value
             *
             * @return this builder
             */
            private Builder withVertices(List<String> vertices){
                setVertices(vertices);
                return this;
            }

            /**
             * Sets Value
             *
             * @param vertices vertices value
             */
            private void setVertices(List<String> vertices) {
                this.vertices = vertices;
            }

            /**
             * Sets transformations
             *
             * @param transformations transformations value
             *
             * @return this builder
             */
            private Builder withTransformations(Set<Entry<String, String>> transformations){
                setTransformations(transformations);
                return this;
            }

            /**
             * Sets transformations
             *
             * @param transformations transformations value
             */
            private void setTransformations(Set<Entry<String, String>> transformations) {
                this.transformations = transformations;
            }

            /**
             * builds parsed polygon
             *
             * @return new parsed polygon
             */
            ParsedFilePolygon build(){
                return new ParsedFilePolygon(color, vertices, transformations);
            }
        }

    }

}
