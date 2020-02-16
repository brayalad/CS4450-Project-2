package com.cpp.cs.cs4450.util.sorting;

public class Sort {

    private Sort(){}

    public static void sort(int[] array){
        QuickSort.sort(array);
    }

    public static void sort(Object[] array){
        MergeSort.sort(array);
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
