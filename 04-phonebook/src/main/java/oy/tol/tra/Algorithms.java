package oy.tol.tra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;


public class Algorithms {

    public static <T extends Comparable<T>> void fastSort(T[] array){
        if(array == null || array.length == 0){
            return;
        }
        fastSort(array, 0, array.length - 1);
    }
    private static <T extends Comparable<T>> void fastSort(T[] array, int start, int end){
        if(start < end){
            int pivotIndex = partition(array, start, end);
            fastSort(array, start, pivotIndex - 1);
            fastSort(array, pivotIndex + 1, end);
        }
    }
    private static <T extends Comparable<T>> int partition(T[] array, int start, int end){
        T pivot = array[end];
        int i = start - 1;
        for(int j = start; j < end; j++){
            if(array[j].compareTo(pivot) < 0){
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, end);
        return i + 1;
    }
    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public static <T> int partitionByRule(T[] array, int length, PartitionRule<T> rule) {
        int begin = 0;
        int end = length - 1;
        while (begin <= end) {
            while (begin <= end && rule.test(array[begin])) {
                begin++;
            }
            while (begin <= end && !rule.test(array[end])) {
                end--;
            }
            if (begin <= end) {
                swap(array, begin, end);
                begin++;
                end--;
            }
        }
        // Return the index where the partition occurs
        return begin;
    }
    public static <T> void sortWithComparator(T[] array, Comparator<T> comparator) {
        fastSortWithComparator(array, comparator, 0, array.length - 1);
    }

    private static <T> void fastSortWithComparator(T[] array,  Comparator<T> comparator, int begin, int end) {

        if(begin < end){
            int pivotIndex = partitionWithComparator(array, comparator, begin, end);
            fastSortWithComparator(array, comparator, begin, pivotIndex - 1);
            fastSortWithComparator(array, comparator, pivotIndex + 1, end);
        }
    }

    private static <T> int partitionWithComparator(T[] array, Comparator<T> comparator, int start, int end) {
        T pivot = array[end];
        int i = start - 1;
        for(int j = start; j < end; j++){
            if(comparator.compare(array[j], pivot) <= 0){
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, end);
        return i + 1;
    }
    public interface PartitionRule<T> {
        boolean test(T element);
    }

}
