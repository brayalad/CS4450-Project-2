package com.cpp.cs.cs4450.util.sorting;

public final class BubbleSort {

    private BubbleSort(){}

    public static void sort(int[] array){
        if(array == null || array.length <= 1)
            return;

        optimizedBubbleSort(array);
    }

    private static void standardBubbleSort(int[] array){
        int n = array.length - 1;
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n - i; ++j){
                if(array[j] > array[j + 1]){
                    Sort.swap(array, j, j + 1);
                }
            }
        }
    }

    private static void optimizedBubbleSort(int[] array){
        boolean sorted = false;

        int n = array.length - 1;
        int i = 0;
        while (!sorted){
            sorted = true;
            for(int j = 0; j < n - i; ++j){
                if(array[j] > array[j + 1]){
                    Sort.swap(array, j, j + 1);
                }
            }
            ++i;
        }
    }







    public static void sort(Object[] array){
        if(array == null || array.length <= 1)
            return;

        optimizedBubbleSort(array);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void standardBubbleSort(Object[] array){
        int n = array.length - 1;
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n - i; ++j){
                if(((Comparable) array[j]).compareTo(array[j + 1]) > 0){
                    Sort.swap(array, j, j + 1);
                }
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void optimizedBubbleSort(Object[] array){
        boolean sorted = false;

        int n = array.length - 1;
        int i = 0;
        while (!sorted){
            sorted = true;
            for(int j = 0; j < n - i; ++j){
                if(((Comparable) array[j]).compareTo(array[j + 1]) > 0){
                    Sort.swap(array, j, j + 1);
                }
            }
            ++i;
        }
    }

}
