package com.cpp.cs.cs4450.util.sorting;

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
        iterativeMergeSort(array, array.clone(), 0, array.length - 1);
    }

    private static void iterativeMergeSort(int[] array, int[] temp, int low, int high){
        for(int i = 1; i <= high - low; i *= 2){
            for(int j = low; j < high; j += i * 2){
                merge(array, temp, j, (j + i - 1), Math.min(j + 2 * i - 1, high));
            }
        }
    }

    public static void recursiveMergeSort(int[] array){
        recursiveMergeSort(array, array.clone(), 0, array.length - 1);
    }

    private static void recursiveMergeSort(int[] array, int[] temp, int low, int high){
        if(low > high) return;

        int mid = (low + high) / 2;
        recursiveMergeSort(array, temp, low, mid);
        recursiveMergeSort(array, temp, mid + 1, high);
        merge(array, temp, low, mid, high);
    }

    private static void merge(int[] array, int[] temp, int low, int mid, int high){
        int i = low;
        int j = mid + 1;
        int k = low;

        while (i <= mid && j <= high){
            if(temp[i] <= temp[j]){
                array[k++] = temp[i++];
            } else {
                array[k++] = temp[j++];
            }
        }

        while(i <= mid){
            array[k++] = temp[i++];
        }
        while(j <= high){
            array[k++] = temp[j++];
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
        iterativeMergeSort(array, array.clone(), 0, array.length - 1);
    }

    @SuppressWarnings("SameParameterValue")
    private static void iterativeMergeSort(Object[] array, Object[] temp, int low, int high){
        for(int i = 1; i <= high - low; i *= 2){
            for(int j = low; j < high; j += i * 2){
                merge(array, temp, j, (j + i - 1), Math.min(j + 2 * i - 1, high));
            }
        }
    }

    public static void recursiveMergeSort(Object[] array){
        recursiveMergeSort(array, array.clone(), 0, array.length - 1);
    }

    private static void recursiveMergeSort(Object[] array, Object[] temp, int low, int high){
        if(low > high) return;

        int mid = (low + high) / 2;
        recursiveMergeSort(array, temp, low, mid);
        recursiveMergeSort(array, temp, (mid + 1), high);
        merge(array, temp, low, mid, high);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void merge(Object[] array, Object[] temp, int low, int mid, int high){
        int i = low;
        int j = mid + 1;
        int k = low;

        while (i <= mid && j <= high){
            if(((Comparable) temp[i]).compareTo(temp[j]) <= 0){
                array[k++] = temp[i++];
            } else {
                array[k++] = temp[j++];
            }
        }

        while(i <= mid){
            array[k++] = temp[i++];
        }
        while(j <= high){
            array[k++] = temp[j++];
        }
    }






}
