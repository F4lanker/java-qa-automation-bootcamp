package ru.qa.day1;

public class NumberClassifier {
    public static String classify(Object input) {
        if (input == null) {
            return "значение не задано";
        }
        if(input.getClass() == String.class) return "некорректное значение";

        if (input instanceof Number number) {
            double value = number.doubleValue();
            if (value > 0) return "положительное";
            else if (value < 0) return "отрицательное";
        }
        return "ноль";
    }
}
