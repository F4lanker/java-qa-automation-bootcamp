package ru.qa.utils;

/**
 * Utility class for measuring execution time of operations
 */
public final class TimeMeasurementUtils {
    private TimeMeasurementUtils() {
        throw new AssertionError("Utility class should not be instantiated");
    }
    
    /**
     * Measures the execution time of a given operation
     * @param operation the operation to measure
     * @return execution time in nanoseconds
     */
    public static long measureExecutionTime(Runnable operation) {
        long start = System.nanoTime();
        operation.run();
        return System.nanoTime() - start;
    }
}
