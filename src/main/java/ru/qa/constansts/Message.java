package ru.qa.constansts;

public class Message {
    private Message(){}

    public static final class Number{
        public static final String POSITIVE = "положительное";
        public static final String NEGATIVE = "отрицательное";
        public static final String ZERO = "ноль";
    }

    public static final class Validation{
        public static final String NULL_VALUE = "значение не задано";
        public static final String INVALID = "некорректное значение";
    }
}
