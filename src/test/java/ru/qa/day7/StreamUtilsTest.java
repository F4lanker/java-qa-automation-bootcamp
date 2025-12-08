package ru.qa.day7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.qa.day4.User;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static ru.qa.day7.StreamUtils.*;
import static testData.ListSamplesForTests.*;
import static testData.UserSamples.*;
import static testData.UsersListsSample.*;

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
            Map<Integer, List<User>> expetedSameAge = Map.of(
                    17, List.of(TEENAGE_USER,TEENAGE_USER_TWO)
            );

            assertEquals(expected, groupByAge(TEENAGE_USER_LIST));
            assertEquals(expetedSameAge, groupByAge(TEENAGE_USER_SAME_AGE));
        }

        @DisplayName("Should return empty list if inptuted list is NULL")
        @Test
        void nullCase(){
            assertEquals(Collections.emptyMap(), groupByAge(NULL_USER_LIST));
        }

        @DisplayName("Should return Empty list, When inputed User list is empty")
        @Test
        void emptyListCase(){
            assertEquals(Collections.emptyMap(), groupByAge(EMPTY_LIST));
        }
    }

    @DisplayName("Test partitionByAdultStatus")
    @Nested
    class PartitionByAdultStatus{

        @DisplayName("Should return Map of adults and non adults Usres")
        @Test
        void positiveCase(){
            Map<Boolean, List<User>> expectedUserList = Map.of(
                    true, List.of(VALID_USER, NULL_VALUE_IN_STRING, SIMILAR_PREFIX_IN_NAME),
                    false, List.of(USER_WITH_EMPTY_STRINGS, TEENAGE_USER, SIMILAR_PREFIX_IN_NAME_2)
            );
            Map<Boolean, List<User>> expectedTeenageList = Map.of(
                    true, Collections.emptyList(),
                    false, List.of(TEENAGE_USER, USER_WITH_EMPTY_STRINGS)
            );
            assertEquals(expectedUserList, partitionByAdultStatus(USER_LIST));
            assertEquals(expectedTeenageList, partitionByAdultStatus(TEENAGE_USER_LIST));

        }

        @DisplayName("Should return empty map if input is Empty or NULL")
        @Test
        void emptyNullTest(){
            assertAll(
                    ()->assertEquals(Collections.emptyMap(), partitionByAdultStatus(EMPTY_LIST)),
                    ()-> assertEquals(Collections.emptyMap(), partitionByAdultStatus(NULL_USER_LIST))
            );
        }
    }
}
