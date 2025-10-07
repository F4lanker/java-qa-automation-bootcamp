package ru.qa.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberClassifierTest {

    @DisplayName("Positive integer case")
    @Test
    public void positiveInteger(){
        Assertions.assertEquals("положительное", NumberClassifier.classify(99999));
        Assertions.assertEquals("положительное", NumberClassifier.classify(23));
    }

    @DisplayName("Negative integer and doubles")
    @Test
    public void negativeNumbers(){
        Assertions.assertEquals("отрицательное", NumberClassifier.classify(-1));
        Assertions.assertEquals("отрицательное", NumberClassifier.classify(-999999));
        Assertions.assertEquals("отрицательное", NumberClassifier.classify(-0.0001));

    }

    @DisplayName("Test for 0")
    @Test
    public void zeroTest(){
        Assertions.assertEquals("ноль", NumberClassifier.classify(-0));
    }

    @DisplayName("Null test")
    @Test
    public void nullTest(){
        Assertions.assertEquals("значение не задано", NumberClassifier.classify(null));
    }

    @DisplayName("Strings")
    @Test
    public void StringTest(){
        Assertions.assertEquals("некорректное значение", NumberClassifier.classify("Lorum ipsum"));
        Assertions.assertEquals("некорректное значение", NumberClassifier.classify("Кирилический текст"));
    }
}
