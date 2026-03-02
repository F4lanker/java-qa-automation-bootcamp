package ru.qa.day3;

public class TernarAgeLimiter {
    public static final int ADULT_AGE = 18;
    private static final String ADULT = "adult";
    private static final String MINOR = "minor";
    public static String ageEstimate(int age){
        return age >= ADULT_AGE ? ADULT : MINOR;
    }
}
