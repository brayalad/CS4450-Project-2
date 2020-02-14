package com.cpp.cs.cs4450.util.collections;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Graph<T> {

    void addVertex(T v);

    void addEdge(T s, T d);

    boolean removeVertex(T v);

    boolean removeEdge(T s, T d);

    int getVertexCount();

    int getEdgesCount();

    boolean hasVertex(T v);

    boolean hasEdge(T s, T d);

    Set<T> getVertices();

    List<T> getAdjacentVertices(T s);

    Collection<Edge<T>> getEdges(T s);

    Collection<Edge<T>> getEdges();

    Map<T, List<T>> toMap();

    interface Edge<T> {

        T getSource();

        T getDestination();

        void setSource(T s);

        void setDestination(T d);

    }

}
