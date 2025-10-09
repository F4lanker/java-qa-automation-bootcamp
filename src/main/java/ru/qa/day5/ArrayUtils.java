package ru.qa.day5;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayUtils {
    String[] names = {"Alice", "Bob", "Charlie", "Diana", "Eve"};
    public static boolean containsArr( String[] array, String name){
        if (array == null || name == null) return false;
        for (String item : array){
            if(name.equals(item)) return true;
        }
        return false;
    }


}
