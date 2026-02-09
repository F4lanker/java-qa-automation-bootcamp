package ru.qa.day1;

import static ru.qa.constansts.Message.Number.*;
import static ru.qa.constansts.Message.Validation.INVALID;
import static ru.qa.constansts.Message.Validation.NULL_VALUE;

public class NumberClassifier {
    public static String classify(Object input) {
        if (input == null) {
            return NULL_VALUE;
        }
        if(input.getClass() == String.class) return INVALID;

        if (input instanceof Number number) {
            double value = number.doubleValue();
            if (value > 0) return POSITIVE;
            else if (value < 0) return NEGATIVE;
        }
        return ZERO;
    }
}
