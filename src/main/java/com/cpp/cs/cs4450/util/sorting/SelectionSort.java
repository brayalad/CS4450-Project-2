package com.cpp.cs.cs4450.util.sorting;

public final class SelectionSort {

    private SelectionSort(){}

    public static void sort(int[] array){
        if(array == null || array.length <= 1)
            return;

        selectionSort(array);
    }

    private static void selectionSort(int[] array){
        int n = array.length;
        for(int i = 0; i < n - 1; ++i){
            int min = i;
            for(int j = i + 1; j < n; ++j){
                min = (array[j] < array[min] ? j : min);
            }
            Sort.swap(array, i, min);
        }
    }







    public static void sort(Object[] array){
        if(array == null || array.length <= 1)
            return;

        selectionSort(array);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void selectionSort(Object[] array){
        int n = array.length;
        for(int i = 0; i < n - 1; ++i){
            int min = i;
            for(int j = i + 1; j < n; ++j){
                min = (((Comparable) array[j]).compareTo(array[min]) < 0) ? j : min;
            }
            Sort.swap(array, i, min);
        }
    }


}
