package com.cpp.cs.cs4450.util.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public final class PolygonCoordinateFileParser {
    public static final String POLYGON_FLAG = "P";
    public static final String TRANSFORMATION_FLAG = "T";
    private static final String NEW_LINE_REG_EX = "\n";
    private static final String STRING_JOIN_DELIMITER = " ";

    private PolygonCoordinateFileParser(){}


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


    public static final class ParsedFilePolygon {
        private final String color;
        private final List<String> vertices;
        private final Set<Entry<String, String>> transformations;


        private ParsedFilePolygon(String color, List<String> vertices, Set<Entry<String, String>> transformations) {
            this.color = color;
            this.vertices = vertices;
            this.transformations = transformations;
        }

        public String getColor() {
            return color;
        }

        public List<String> getVertices() {
            return vertices;
        }

        public Set<Entry<String, String>> getTransformations() {
            return transformations;
        }

        private static Builder builder(){
            return new Builder();
        }

        @Override
        public String toString(){
            return "Parsed File Polygon:\n" +
                    "\tColor:\t" + color + "\n" +
                    "\tVertices:\t" + vertices.toString() + "\n" +
                    "\tTransformations:\t" + transformations.toString() + "\n";
        }

        private static final class Builder {
            private String color;
            private List<String> vertices;
            private Set<Entry<String, String>> transformations;


            private Builder(){}

            private Builder withColor(String color){
                setColor(color);
                return this;
            }

            private String getColor() {
                return color;
            }

            private void setColor(String color) {
                this.color = color;
            }

            private Builder withVertices(List<String> vertices){
                setVertices(vertices);
                return this;
            }

            private List<String> getVertices() {
                return vertices;
            }

            private void setVertices(List<String> vertices) {
                this.vertices = vertices;
            }

            private Builder withTransformations(Set<Entry<String, String>> transformations){
                setTransformations(transformations);
                return this;
            }

            private Set<Entry<String, String>> getTransformations() {
                return transformations;
            }

            private void setTransformations(Set<Entry<String, String>> transformations) {
                this.transformations = transformations;
            }

            ParsedFilePolygon build(){
                return new ParsedFilePolygon(color, vertices, transformations);
            }
        }

    }



}
