package ru.qa.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Simple logger utility for tests.
 */
public class Logger {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    private final String testName;

    public Logger(String testName) {
        this.testName = testName;
    }

    public void info(String message) {
        System.out.println(String.format("[%s] [%s] INFO: %s",
                                         LocalDateTime.now().format(FORMATTER),
                                         testName,
                                         message));
    }

    public void error(String message) {
        System.err.println(String.format("[%s] [%s] ERROR: %s",
                                         LocalDateTime.now().format(FORMATTER),
                                         testName,
                                         message));
    }
}