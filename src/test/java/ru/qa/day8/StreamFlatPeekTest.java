package ru.qa.day8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.qa.day4.User;
import ru.qa.day7.StreamUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.qa.day8.StreamFlatPeek.flattenUserEmails;
import static testData.UserSamples.*;
import static testData.UsersListsSample.*;

public class StreamFlatPeekTest {

    @Nested
    @DisplayName("test for flatMap function")
    class flatMapTest {

        @DisplayName("Should return the list of emails against in positive case")
        @Test
        void positiveCase() {
            List<List<User>> listOfListValid = List.of(
                    List.of(VALID_USER, SIMILAR_PREFIX_IN_NAME), // "alice@test.com", "don@ald.com"
                    TEENAGE_USER_SAME_AGE);                          // "bob@test.com", "dSam@test.com"
            List<String> expected = List.of("alice@test.com", "don@ald.com", "bob@test.com", "dSam@test.com");

            assertEquals(expected, flattenUserEmails(listOfListValid));
        }

        @DisplayName("Should empty list if the List of List is NULL")
        @Test
        void nullListOfList(){
            assertEquals(Collections.emptyList(), flattenUserEmails(null));
        }
        @DisplayName("Should return list of email for the rest of list, if one of the lists is NULL")
        @Test
        void oneOfListisNull(){
            List<List<User>> oneListIsNull = Arrays.asList(
                List.of(VALID_USER, TEENAGE_USER_TWO),  //"alice@test.com", "dSam@test.com"
                    null
            );
            List<String> expected = List.of("alice@test.com", "dSam@test.com");

            assertEquals(expected, flattenUserEmails(oneListIsNull));
        }

        @DisplayName("Should return empty value against NULL or empty EMAIL") //"Should return list of email for the rest of list, if one of the lists is NULL"
        @Test
        void emailIsNullOrEmpty(){
            List<List<User>> emailIsNullOeEmpty = List.of(
                    ONE_USER_18YO,          //"", "bob@test.com", null
                    TEENAGE_USER_LIST       //"bob@test.com", null
            );
            List<String> expected = List.of("", "bob@test.com", "bob@test.com", "");

            assertEquals(expected, flattenUserEmails(emailIsNullOeEmpty));
        }
    }

    @Test
    @DisplayName("Should collect users into UserSummary")
    void shouldCollectIntoUserSummary() {
        List<User> users = Arrays.asList(
                new User(1, "Alice", "a@test.com", 25),
                new User(2, "Bob", "b@test.com", 30),
                new User(3, "Charlie", "c@test.com", 18)
        );

        UserSummary summary = users.stream()
                .collect(StreamUtils.toUserSummary());

        assertEquals(3, summary.getCount());
        assertEquals(18, summary.getMinAge());
        assertEquals(30, summary.getMaxAge());
        assertEquals(24.33, summary.getAverageAge(), 0.01);
    }
}
