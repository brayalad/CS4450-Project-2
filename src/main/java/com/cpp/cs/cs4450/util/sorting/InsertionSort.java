package com.cpp.cs.cs4450.util.sorting;

public final class InsertionSort {

    private InsertionSort(){}

    public static void sort(int[] array){
        if(array == null || array.length <= 1)
            return;

        insertionSort(array);
    }

    private static void insertionSort(int[] array){
        int n = array.length;
        for(int i = 0; i < n; ++i){
            int j = i;
            while(j > 0 && array[j] < array[j - 1]){
                Sort.swap(array, j, --j);
            }
        }
    }


    public static void sort(Object[] array){
        if(array == null || array.length <= 1)
            return;

        insertionSort(array);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void insertionSort(Object[] array){
        int n = array.length;
        for(int i = 0; i < n; ++i){
            int j = i;
            while(j > 0 && ((Comparable) array[j]).compareTo(array[j - 1]) < 0){
                Sort.swap(array, j, --j);
            }
        }
    }

}
