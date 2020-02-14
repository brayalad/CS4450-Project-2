package com.cpp.cs.cs4450.util.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractGraph<T> implements Graph<T> {
    private final Map<T, List<T>> graph = new LinkedHashMap<>();

    public AbstractGraph(){}

    public AbstractGraph(final Map<T, List<T>> graph){
        this.graph.putAll(graph);
    }

    public AbstractGraph(final Graph<T> graph){
        this(graph.toMap());
    }

    @Override
    public void addVertex(final T v){
        graph.put(v, new LinkedList<>());
    }

    protected void addEdge(final T source, final T destination, final boolean bidirectional){
        if(!graph.containsKey(source))
            addVertex(source);

        if(!graph.containsKey(destination))
            addVertex(destination);


        graph.get(source).add(destination);
        if(bidirectional)
            graph.get(destination).add(source);

    }

    @Override
    public boolean removeVertex(final T v){
        if(!hasVertex(v)) return false;

        graph.remove(v);
        for(final T s : graph.keySet()){
            graph.get(s).remove(v);
        }

        return true;
    }


    protected boolean removeEdge(final T s, final T d, final boolean bidirectional){
        if(!hasEdge(s, d)) return false;

        graph.get(s).remove(d);
        if(bidirectional)
            graph.get(d).remove(s);

        return true;
    }

    @Override
    public int getVertexCount(){
        return graph.keySet().size();
    }

    protected int getEdgesCount(final boolean bidirectional){
        int count = 0;
        for(final T v : graph.keySet())
            count += graph.get(v).size();

        return (bidirectional) ? (count / 2) : count;
    }

    @Override
    public boolean hasVertex(final T s){
        return graph.containsKey(s);
    }

    @Override
    public boolean hasEdge(final T s, final T d){
        return graph.get(s).contains(d);
    }

    @Override
    public Set<T> getVertices(){
        return new LinkedHashSet<>(graph.keySet());
    }

    @Override
    public List<T> getAdjacentVertices(final T s){
        return graph.get(s);
    }

    @Override
    public Collection<Edge<T>> getEdges(final T s){
        final List<Edge<T>> edges = new ArrayList<>();
        for(final T d : graph.get(s)){
            edges.add(new UnweightedEdge<>(s, d));
        }

        return edges;
    }

    @Override
    public Collection<Edge<T>> getEdges(){
        final List<Edge<T>> edges = new ArrayList<>();
        for(final T v : graph.keySet()){
            edges.addAll(getEdges(v));
        }

        return edges;
    }

    @Override
    public Map<T, List<T>> toMap(){
        return new LinkedHashMap<>(graph);
    }

    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        for(final T v : graph.keySet()){
            sb.append(v.toString()).append(": ");
            for(final T w : graph.get(v)){
                sb.append(w.toString()).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }


    public static class UnweightedEdge<T> implements Edge<T> {
        private T source;
        private T destination;

        public UnweightedEdge(final T source, final T destination){
            this.source = source;
            this.destination = destination;
        }

        @Override
        public T getSource() {
            return source;
        }

        @Override
        public T getDestination() {
            return destination;
        }

        @Override
        public void setSource(final T source) {
            this.source = source;
        }

        @Override
        public void setDestination(final T destination) {
            this.destination = destination;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(final Object obj){
            if(obj == null) return false;
            if(obj == this) return true;
            if(obj.getClass() != getClass()){
                return false;
            }

            final UnweightedEdge<T> other = (UnweightedEdge<T>) obj;

            return Objects.equals(this.source, other.source) && Objects.equals(this.destination, other.destination);
        }

    }

}
