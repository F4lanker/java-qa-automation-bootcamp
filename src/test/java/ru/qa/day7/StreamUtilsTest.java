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

    @DisplayName("Test getAgeStatistic")
    @Nested
    class AgeStatistic{

        @DisplayName("Should return statistic against inputed list")
        @Test
        void positiveCase(){
            assertAll(
                    ()-> assertEquals(3, getAgeStatistics(ONE_USER_18YO).getCount(), 0.001), // there are 3 users in the list
                    ()-> assertEquals(1, getAgeStatistics(ONE_USER_18YO).getMin(), 0.001), // USER_WITH_EMPTY_STRING age = 1
                    ()-> assertEquals(18, getAgeStatistics(ONE_USER_18YO).getMax(), 0.001), // NULL_VALUE_IN_STRINGS age = 18
                    ()-> assertEquals(12, getAgeStatistics(ONE_USER_18YO).getAverage(), 0.001) // average age is 12 y.o = (18 + 17 + 1) / 3
            );
        }

        @DisplayName("Should return statistics if there is only one user in the list")
        @Test
        void oneUserTest(){
            List<User> input = List.of(TEENAGE_USER); //(4, "Bob", "bob@test.com", 17);

            assertAll(
                    ()-> assertEquals(1, getAgeStatistics(input).getCount(), 0.001),//there is the only one user in the list
                    ()-> assertEquals(17, getAgeStatistics(input).getMin(), 0.001), //MIN, MAX and average  age is the same
                    ()-> assertEquals(17, getAgeStatistics(input).getMax(), 0.001),
                    ()-> assertEquals(17, getAgeStatistics(input).getAverage(), 0.001)
            );
        }


        @DisplayName("Should return statistics if list is NULL or Empty")
        @Test
        void emptyNullTest(){
            DoubleSummaryStatistics emptyStat = getAgeStatistics(EMPTY_LIST);
            DoubleSummaryStatistics nullStat = getAgeStatistics(NULL_USER_LIST);

            assertAll(
                    ()-> assertEquals(0, emptyStat.getCount()),
                    () -> assertEquals(0, emptyStat.getAverage(), 0.001),
                    ()-> assertEquals(Double.POSITIVE_INFINITY, emptyStat.getMin()),
                    () -> assertEquals(Double.NEGATIVE_INFINITY, emptyStat.getMax())
            );

            assertAll(
                    ()-> assertEquals(0, nullStat.getCount()),
                    () -> assertEquals(0, nullStat.getAverage(), 0.001),
                    ()-> assertEquals(Double.POSITIVE_INFINITY, nullStat.getMin()),
                    () -> assertEquals(Double.NEGATIVE_INFINITY, nullStat.getMax())
            );
        }

            }
        }

