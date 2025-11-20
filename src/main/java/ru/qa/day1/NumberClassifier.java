package ru.qa.day1;

/**
 * Utility class for classifying numbers as positive, negative, or zero
 */
public class NumberClassifier {
    
    /**
     * Enum representing the possible classifications of a number
     */
    public enum NumberType {
        POSITIVE("положительное"),
        NEGATIVE("отрицательное"),
        ZERO("ноль"),
        UNDEFINED("значение не задано"),
        INVALID("некорректное значение");
        
        private final String description;
        
        NumberType(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    /**
     * Classifies a number as positive, negative, zero, undefined, or invalid
     * @param input the input to classify
     * @return the classification result as a string
     */
    public static String classify(Object input) {
        if (input == null) {
            return NumberType.UNDEFINED.getDescription();
        }
        if (input instanceof String) {
            return NumberType.INVALID.getDescription();
        }
        if (input instanceof Number number) {
            double value = number.doubleValue();
            if (value > 0) {
                return NumberType.POSITIVE.getDescription();
            } else if (value < 0) {
                return NumberType.NEGATIVE.getDescription();
            } else {
                return NumberType.ZERO.getDescription();
            }
        }
        return NumberType.INVALID.getDescription();
    }
    
    /**
     * Classifies a number and returns the enum value
     * @param input the input to classify
     * @return the classification as an enum
     */
    public static NumberType classifyAsEnum(Object input) {
        if (input == null) {
            return NumberType.UNDEFINED;
        }
        if (input instanceof String) {
            return NumberType.INVALID;
        }
        if (input instanceof Number number) {
            double value = number.doubleValue();
            if (value > 0) {
                return NumberType.POSITIVE;
            } else if (value < 0) {
                return NumberType.NEGATIVE;
            } else {
                return NumberType.ZERO;
            }
        }
        return NumberType.INVALID;
    }
}
