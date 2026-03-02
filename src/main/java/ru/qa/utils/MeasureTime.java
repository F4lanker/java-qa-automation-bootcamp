package ru.qa.utils;

public class MeasureTime {
    public static long measureTime(Runnable operation) {
        long start = System.nanoTime();
        operation.run();
        return System.nanoTime() - start;
    }
}
