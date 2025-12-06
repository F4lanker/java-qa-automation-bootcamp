package ru.qa.day7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.qa.day4.User;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static ru.qa.day7.StreamUtils.groupByAge;
import static ru.qa.day7.StreamUtils.toUpperCase;
import static testData.ListSamplesForTests.*;
import static testData.UserSamples.TEENAGE_USER;
import static testData.UserSamples.USER_WITH_EMPTY_STRINGS;
import static testData.UsersListsSample.TEENAGE_USER_LIST;

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
        @Test
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
    @DisplayName("Test groupByAge")
    @Nested
    class GroupedByAge {

        @DisplayName("Should return dictionary with users gouped by Age")
        @Test
        void positiveCase(){
            Map <Integer, List<User>> expected = Map.of(
                    17, List.of(TEENAGE_USER),
                    1, List.of(USER_WITH_EMPTY_STRINGS)
            );

            assertEquals(expected, groupByAge(TEENAGE_USER_LIST));
        }
    }
}
