package com.cpp.cs.cs4450.util.sorting;

import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public final class MergeSort {

    private MergeSort(){}

    public static void sort(int[] array){
        if(array == null || array.length <= 1)
            return;

        try{
            recursiveMergeSort(array);
        } catch (StackOverflowError e){
            System.err.println(e.getLocalizedMessage());
        } finally {
            iterativeMergeSort(array);
        }
    }

    public static void iterativeMergeSort(int[] array){
        int[] temp = new int[array.length];
        for(int i = 1; i < array.length; i *= 2){
            for(int j = 0; j < array.length - i; j += 2 * i){
                merge(array, temp, j, (j + i - 1), Math.min(j + (2 * i) - 1, array.length - 2));
            }
        }
    }


    public static void recursiveMergeSort(int[] array){
        recursiveMergeSort(array, new int[array.length],0, array.length - 1);
    }

    private static void recursiveMergeSort(int[] array, int[] temp,  int low, int high){
        if(low >= high) return;

        int mid = low + (high - low) / 2;
        recursiveMergeSort(array, temp, low, mid);
        recursiveMergeSort(array, temp, (mid + 1), high);

        if(!(array[mid + 1] < array[mid])) return;

        merge(array, temp, low, mid, high);
    }

    private static void merge(int[] array, int[] temp, int low, int mid, int high){
        int i = low;
        int j = mid + 1;
        int k = low;

        while(i <= mid && j <= high){
            temp[k++] = (array[i] <= array[j]) ? array[i++] : array[j++];
        }

        while(i <= mid){
            temp[k++] = array[i++];
        }
        while(j <= high){
            temp[k++] = array[j++];
        }

        if (high + 1 - low >= 0) {
            System.arraycopy(temp, low, array, low, high + 1 - low);
        }
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T extends Comparable> void sort(List<T> list){
        Object[] a = list.toArray();
        sort(a);
        ListIterator<T> i = list.listIterator();
        for(Object e : a){
            i.next();
            i.set((T) e);
        }
    }

    public static void sort(Object[] array){
        if(array == null || array.length <= 1)
            return;

        try{
            recursiveMergeSort(array);
        } catch (StackOverflowError e){
            iterativeMergeSort(array);
        }
    }

    public static void iterativeMergeSort(Object[] array){
        Object[] temp = new Object[array.length];
        for(int i = 1; i < array.length; i *= 2){
            for(int j = 0; j < array.length - i; j += 2 * i){
                merge(array, temp, j, (j + i - 1), Math.min(j + (2 * i) - 1, array.length - 2));
            }
        }
    }

    public static void recursiveMergeSort(Object[] array){
        recursiveMergeSort(array, array.clone(), 0, array.length - 1);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void recursiveMergeSort(Object[] array, Object[] temp, int low, int high){
        if(low > high) return;

        int mid = (low + high) / 2;
        recursiveMergeSort(array, temp, low, mid);
        recursiveMergeSort(array, temp, (mid + 1), high);

        if(!(((Comparable) array[mid + 1]).compareTo(array[mid]) < 0)) return;

        merge(array, temp, low, mid, high);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void merge(Object[] array, Object[] temp, int low, int mid, int high){
        int i = low;
        int j = mid + 1;
        int k = low;

        while(i <= mid && j <= high){
            temp[k++] = (((Comparable) array[i]).compareTo(array[j]) <= 0) ? array[i++] : array[j++];
        }

        while(i <= mid){
            temp[k++] = array[i++];
        }
        while(j <= high){
            temp[k++] = array[j++];
        }

        if (high + 1 - low >= 0) {
            System.arraycopy(temp, low, array, low, high + 1 - low);
        }
    }

}
