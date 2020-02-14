package com.cpp.cs.cs4450.util.collections;

import java.util.List;
import java.util.Map;

public class DirectedGraph<T> extends AbstractGraph<T> implements Graph<T> {
    private static final boolean BIDIRECTIONAL = false;

    public DirectedGraph(){
        super();
    }

    public DirectedGraph(final Map<T, List<T>> graph){
        super(graph);
    }

    public DirectedGraph(final Graph<T> graph){
        this(graph.toMap());
    }

    @Override
    public void addEdge(final T s, final T d) {
        addEdge(s, d, BIDIRECTIONAL);
    }

    @Override
    public boolean removeEdge(final T s, final T d) {
        return removeEdge(s, d, BIDIRECTIONAL);
    }

    @Override
    public int getEdgesCount() {
        return getEdgesCount(BIDIRECTIONAL);
    }

}
