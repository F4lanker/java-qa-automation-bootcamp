package ru.qa.day5;

import java.util.ArrayList;
import java.util.Arrays;

public final class ArrayUtils {
    private ArrayUtils(){
        throw new AssertionError("Нельзя создавать экземпляр утилитного класса");
    }
    public static boolean containsArr( String[] array, String name){
        if (array == null || name == null) return false;
        for (String item : array){
            if(name.equals(item)) return true;
        }
        return false;
    }


}
