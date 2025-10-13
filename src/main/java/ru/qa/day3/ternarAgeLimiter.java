package ru.qa.day3;

public class ternarAgeLimiter {
    public static String ageEstimate(int age){
        return age >= 18 ? "adult" : "minor";
    }
}
