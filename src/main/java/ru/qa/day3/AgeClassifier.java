package ru.qa.day3;

/**
 * Utility class for age-based classification
 */
public class AgeClassifier {
    /**
     * Determines if a person is an adult or minor based on age
     * @param age the age to classify
     * @return "adult" if age >= 18, otherwise "minor"
     */
    public static String classifyAge(int age) {
        return age >= 18 ? "adult" : "minor";
    }
}
