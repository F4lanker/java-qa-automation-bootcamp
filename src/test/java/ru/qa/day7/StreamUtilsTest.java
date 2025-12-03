package ru.qa.day7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static ru.qa.day7.StreamUtils.toUpperCase;
import static testData.ListSamplesForTests.*;

public class StreamUtilsTest {
    @Nested
    @DisplayName("Test toUpperCase")
    class ToUppercaseTests{

        @DisplayName("Should return the List of inserted string in UPPER CASE")
        @Test
        void shouldConvertToUpperCase(){
            List<String> input = List.of("sample", "world");
            Set<String> result = Set.of("SAMPLE", "WORLD");
            assertEquals(result, new HashSet<>(toUpperCase(input)));
        }

        @DisplayName("Should remove duplicates")
        void shouldRemoveDuplicates(){
            assertAll(
                    ()-> assertTrue(toUpperCase(REPEAT_ELEMENTS).contains("SAMPLE")),
                    ()-> assertEquals(1, toUpperCase(REPEAT_ELEMENTS).size())
            );
        }

        @DisplayName("Should return empty list if input is NULL or Empty list")
        @Test
        void nullInput(){
            assertAll(
                    ()-> assertEquals(Collections.emptyList(), toUpperCase(NULL_LIST)),
                    ()-> assertEquals(Collections.emptyList(), toUpperCase(EMPTIED_LIST))
            );
        }

        @DisplayName("Should return values in upper case if one of the value in the list is NULL")
        @Test
        void nullInTheList(){
            Set<String> nullAndOtherElementsResult = Set.of("ALICE");
            Set<String> nullAndOtherElementsActual = new HashSet<>(toUpperCase(NULL_AND_OTHER_DATA_LIST));

            assertEquals(nullAndOtherElementsActual, nullAndOtherElementsResult);
        }
}
}
