package ru.qa.day6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.day4.User;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static ru.qa.day6.UserStreamUtils.*;
import static testData.UserSamples.*;
import static testData.UsersListsSample.*;


public class UserStreamUtilsTest {
    @Test
    @DisplayName("test function returns user whose age is above 18")
    void userAgeAbove18Test() {
        Optional<List<User>> userList = filterAdults(USER_LIST);
        Optional<List<User>> emptyList = filterAdults(EMPTY_LIST);
        Optional<List<User>> nullList = filterAdults(NULL_USER_LIST);
        Optional<List<User>> teenageUsers = filterAdults(TEENAGE_USER_LIST);

        assertAll(
                () -> assertEquals(3, userList.get().size(), "List should consists 2 elements"),
                () -> assertTrue(userList.get().contains(VALID_USER), "List should include VALID_USER"),
                () -> assertTrue(userList.get().contains(NULL_VALUE_IN_STRING), "List should include  NULL_VALUE_IN STRING"),
                () -> assertEquals(Optional.empty(), emptyList, "Optional.empty() returns if the list is empty"),
                () -> assertEquals(Optional.empty(), nullList, "Optional.empty() returns if the list NULL"),
                () -> assertEquals(0, teenageUsers.get().size(), "List should consists 0 elemets, all user below 18")
        );
    }

    @Test
    @DisplayName("test function find user by email")
    void findUserByEmailTest() {
        Optional<User> userListBob = findUserByEmail(USER_LIST, "bob@test.com");
        Optional<User> emptyEmail = findUserByEmail(USER_LIST, String.valueOf(Optional.empty()));
        Optional<User> emptyList = findUserByEmail(EMPTY_LIST, "bob@test.com");
        Optional<User> emptyListEmptyEmail = findUserByEmail(EMPTY_LIST, String.valueOf(Optional.empty()));
        Optional<User> nullList = findUserByEmail(NULL_USER_LIST, "bob@test.com");
        Optional<User> nullListEmptyEmail = findUserByEmail(NULL_USER_LIST, "bob@test.com");
        assertAll(
                () -> assertTrue(userListBob.isPresent(), "User is present"),
                () -> assertEquals(TEENAGE_USER, userListBob.get(), "Found user should match expected"),
                () -> assertEquals(Optional.empty(), emptyEmail),
                () -> assertEquals(Optional.empty(), emptyList),
                () -> assertEquals(Optional.empty(), emptyListEmptyEmail),
                () -> assertEquals(Optional.empty(), nullList),
                () -> assertEquals(Optional.empty(), nullListEmptyEmail)

        );
    }

    @Test
    @DisplayName("test of func returns the list of Names")
    void extractNamesTest() {
        List<String> expected = USER_LIST.stream()
                .filter(Objects::nonNull)
                .map(User::getName)
                .toList();

        assertAll(
                () -> assertEquals(expected, extractNames(USER_LIST), "Positive case with User List"),
                () -> assertEquals(Collections.emptyList(), extractNames(EMPTY_LIST), "Empty list test"),
                () -> assertEquals(Collections.emptyList(), extractNames(NULL_USER_LIST), "Test with null list")
        );
    }

    @Test
    @DisplayName("test for func returns true, if found at least one user over inserted age")
    void hasUserWithAgeOverTest() {
        assertAll(
                () -> assertTrue(hasUserWithAgeOver(USER_LIST, 18)),
                () -> assertTrue(hasUserWithAgeOver(ONE_USER_18YO, 18)),
                () -> assertTrue(hasUserWithAgeOver(USER_LIST, 0)),
                () -> assertFalse(hasUserWithAgeOver(USER_LIST, 24)),
                () -> assertFalse(hasUserWithAgeOver(TEENAGE_USER_LIST, 18)),
                () -> assertFalse(hasUserWithAgeOver(EMPTY_LIST, 0)),
                () -> assertFalse(hasUserWithAgeOver(NULL_USER_LIST, 0))

        );
    }

    @Test
    @DisplayName("test of function returns the first user Name which name starts with prefix")
    void findFirstByNameStartingWithTest() {
        assertAll(
                () -> assertEquals(Optional.of(TEENAGE_USER.getName()), findFirstByNameStartingWith(USER_LIST, "Bo")),
                () -> assertEquals(Optional.of(SIMILAR_PREFIX_IN_NAME.getName()), findFirstByNameStartingWith(USER_LIST, "Donald")),
                () -> assertEquals(Optional.empty(), findFirstByNameStartingWith(USER_LIST, "Zz")),
                () -> assertEquals(Optional.empty(), findFirstByNameStartingWith(NULL_USER_LIST, "Bob")),
                () -> assertEquals(Optional.empty(), findFirstByNameStartingWith(EMPTY_LIST, "")),
                () -> assertEquals(Optional.of(VALID_USER.getName()), findFirstByNameStartingWith(USER_LIST, ""))

        );
    }

}
