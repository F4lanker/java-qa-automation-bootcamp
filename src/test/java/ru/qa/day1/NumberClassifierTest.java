package ru.qa.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ru.qa.constansts.Message.Number.*;
import static ru.qa.constansts.Message.Validation.INVALID;
import static ru.qa.constansts.Message.Validation.NULL_VALUE;

public class NumberClassifierTest {

    @DisplayName("Positive integer case")
    @Test
    public void positiveInteger(){
        Assertions.assertEquals(POSITIVE, NumberClassifier.classify(99999));
        Assertions.assertEquals(POSITIVE, NumberClassifier.classify(23));
    }

    @DisplayName("Negative integer and doubles")
    @Test
    public void negativeNumbers(){
        Assertions.assertEquals(NEGATIVE, NumberClassifier.classify(-1));
        Assertions.assertEquals(NEGATIVE, NumberClassifier.classify(-999999));
        Assertions.assertEquals(NEGATIVE, NumberClassifier.classify(-0.0001));

    }

    @DisplayName("Test for 0")
    @Test
    public void zeroTest(){
        Assertions.assertEquals(ZERO, NumberClassifier.classify(-0));
    }

    @DisplayName("Null test")
    @Test
    public void nullTest(){
        Assertions.assertEquals(NULL_VALUE, NumberClassifier.classify(null));
    }

    @DisplayName("Strings")
    @Test
    public void StringTest(){
        Assertions.assertEquals(INVALID, NumberClassifier.classify("Lorum ipsum"));
        Assertions.assertEquals(INVALID, NumberClassifier.classify("Кирилический текст"));
    }
}
