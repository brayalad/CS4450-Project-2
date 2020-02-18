package com.cpp.cs.cs4450.util.sorting;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public final class Sort {

    private Sort(){}

    public static void sort(int[] array){
        QuickSort.sort(array);
    }

    public static void sort(Object[] array){
        MergeSort.sort(array);
    }

    public static <T extends Comparable<T>> void sort(final List<T> list){
        if(isInOrder(list)) return;

        MergeSort.sort(list);

        if(!isInOrder(list))
            Collections.sort(list);
    }

    public static <T extends Comparable<T>> boolean isInOrder(final Collection<T> collection){
        return (isInOrder(collection, Comparable::compareTo));
    }

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


    static void swap(byte[] a, int i, int j){
        byte t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    static void swap(short[] a, int i, int j){
        short t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    static void swap(long[] a, int i, int j){
        long t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    static void swap(float[] a, int i, int j){
        float t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    static void swap(double[] a, int i, int j){
        double t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    static void swap(boolean[] a, int i, int j){
        boolean t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    static void swap(char[] a, int i, int j){
        char t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    static void swap(Object[] a, int i, int j){
        Object t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

}
