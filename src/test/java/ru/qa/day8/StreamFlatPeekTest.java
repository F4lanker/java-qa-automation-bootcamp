package ru.qa.day8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.qa.day4.User;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.qa.day8.StreamFlatPeek.flattenUserEmails;
import static testData.UserSamples.SIMILAR_PREFIX_IN_NAME;
import static testData.UserSamples.VALID_USER;
import static testData.UsersListsSample.TEENAGE_USER_SAME_AGE;

public class StreamFlatPeekTest {

    @Nested
    @DisplayName("test for flatMap function")
    class flatMapTest {

        @DisplayName("Should return the list of emails against in positive case")
        @Test
        void positiveCase() {
            List<List<User>> listOfListValid = Arrays.asList(
                    List.of(VALID_USER, SIMILAR_PREFIX_IN_NAME), // "alice@test.com",
                    TEENAGE_USER_SAME_AGE);                          // "bob@test.com",
            List<String> expectd = List.of("alice@test.com", "don@ald.com",  "bob@test.com", "dSam@test.com");

            assertEquals(expectd, flattenUserEmails(listOfListValid));
            System.out.println(flattenUserEmails(listOfListValid));
        }
    }
}
