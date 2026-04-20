package ru.qa.utils;

import org.jetbrains.annotations.Contract;

import java.util.List;

public class ListUtils {
    @Contract(" -> fail")
    private ListUtils() {
        throw new AssertionError("Utility class");
    }

    /**
     * Checks if list is not null and not empty.
     */
    public static boolean isNonEmpty(List<?> list){
        return list != null && !list.isEmpty();
    }

    /**
     * Checks if list is null or empty.
     */
    public static boolean isEmpty(List<?> list){
        return list == null || list.isEmpty();
    }

    /**
     * Returns list size or 0 if null.
     */
    public static int safeSize(List<?> list){
        return list == null ? 0 : list.size();
    }
}
