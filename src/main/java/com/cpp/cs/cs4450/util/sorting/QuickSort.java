package com.cpp.cs.cs4450.util.sorting;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.ListIterator;

public final class QuickSort {

    private QuickSort(){}

    public static void sort(int[] array){
        if(array == null || array.length <= 1)
            return;

        try{
            recursiveQuickSort(array);
        } catch (StackOverflowError e){
            System.err.println(e.getLocalizedMessage());
        } finally {
            iterativeQuickSort(array);
        }
    }

    public static void recursiveQuickSort(int[] array){
        recursiveQuickSort(array, 0, array.length - 1);
    }

    private static void recursiveQuickSort(int[] array, int low, int high){
      if(low > high) return;

      int index = partition(array, low, high);

      if(low < index){
          recursiveQuickSort(array, low, index);
      }
      if((index + 1) < high){
          recursiveQuickSort(array, (index + 1), high);
      }
    }

    public static void iterativeQuickSort(int[] array){
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(0);
        stack.addFirst(array.length);

        while(!stack.isEmpty()){
            int high = stack.removeFirst();
            int low = stack.removeFirst();
            if(high - low < 2){
                continue;
            }

            int index = partition(array, low, high);

            stack.addFirst(index + 1);
            stack.addFirst(high);

            stack.addFirst(low);
            stack.addFirst(index);
        }
    }

    private static int partition(int[] array, int low, int high){
        int pivot = array[(low + ((high - low) / 2))];
        while(low <= high){
            while(array[low] < pivot){
                ++low;
            }
            while(array[high] > pivot){
                --high;
            }
            if(low <= high){
                Sort.swap(array, low++, high--);
            }
        }

        return low;
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
            //recursiveQuickSort(array);
            iterativeQuickSort(array);
        } catch (StackOverflowError e){
            iterativeQuickSort(array);
        }
    }

    public static void recursiveQuickSort(Object[] array){
        recursiveQuickSort(array, 0, array.length - 1);
    }

    private static void recursiveQuickSort(Object[] array, int low, int high){
        if(low > high) return;

        int index = partition(array, low, high);

        if(low < index - 1){
            recursiveQuickSort(array, low, (index - 1));
        }
        if(index < high){
            recursiveQuickSort(array, index, high);
        }
    }

    public static void iterativeQuickSort(Object[] array){
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(0);
        stack.addFirst(array.length - 1);

        while(!stack.isEmpty()){
            int high = stack.removeFirst();
            int low = stack.removeFirst();
            if(high - low < 2){
                continue;
            }

            int index = partition(array, low, high);

            stack.addFirst(index);
            stack.addFirst(high);

            stack.addFirst(low);
            stack.addFirst(index - 1);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static int partition(Object[] array, int low, int high){
        Object pivot = array[(low + ((high - low) / 2))];
        while(low <= high){
            while (((Comparable) array[low]).compareTo(pivot) < 0){
                ++low;
            }
            while(((Comparable) array[high]).compareTo(pivot) < 1){
                --high;
            }
            if(low <= high){
                Sort.swap(array, low++, high--);
            }
        }

        return low;
    }




}
