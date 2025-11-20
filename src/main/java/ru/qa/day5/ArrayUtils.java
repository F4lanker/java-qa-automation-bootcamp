package ru.qa.day5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for common array operations
 */
public final class ArrayUtils {
    private ArrayUtils() {
        throw new AssertionError("Utility class should not be instantiated");
    }
    
    /**
     * Checks if an array contains a specific element
     * @param array the array to search in
     * @param element the element to search for
     * @return true if the element is found, false otherwise
     */
    public static boolean contains(String[] array, String element) {
        if (array == null || element == null) {
            return false;
        }
        for (String item : array) {
            if (element.equals(item)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if an array contains a specific element using binary search
     * Note: The array must be sorted for this method to work correctly
     * @param sortedArray the sorted array to search in
     * @param element the element to search for
     * @return true if the element is found, false otherwise
     */
    public static boolean containsSorted(String[] sortedArray, String element) {
        if (sortedArray == null || element == null) {
            return false;
        }
        int result = Arrays.binarySearch(sortedArray, element);
        return result >= 0;
    }
    
    /**
     * Converts an array to an unmodifiable list
     * @param array the array to convert
     * @param <T> the type of elements in the array
     * @return an unmodifiable list containing the array elements
     */
    @SafeVarargs
    public static <T> List<T> asUnmodifiableList(T... array) {
        if (array == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(Arrays.asList(array));
    }
    
    /**
     * Finds the index of the first occurrence of an element in an array
     * @param array the array to search in
     * @param element the element to search for
     * @return the index of the first occurrence, or -1 if not found
     */
    public static int indexOf(String[] array, String element) {
        if (array == null || element == null) {
            return -1;
        }
        for (int i = 0; i < array.length; i++) {
            if (element.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }
}
