package ru.qa.day6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.day4.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static ru.qa.day6.UserStreamUtils.filterAdults;
import static ru.qa.day6.UserStreamUtils.findUserByEmail;
import static testData.UserSamples.*;
import static testData.UsersListsSample.EMPTY_LIST;
import static testData.UsersListsSample.NULL_USER_LIST;


public class UserStreamUtilsTest {
    @Test
    @DisplayName("test function returns user whose age is above 18")
    void userAgeAbove18Test(){
        Optional<List<User>> userList = filterAdults(USER_LIST);
        Optional<List<User>> emptyList = filterAdults(EMPTY_LIST);
        Optional<List<User>> nullList = filterAdults(NULL_USER_LIST);

        assertAll(
                () -> assertEquals(2, userList.get().size(), "List should consists 2 elements"),
                () -> assertTrue(userList.get().contains(VALID_USER), "List should include VALID_USER"),
                () ->  assertTrue(userList.get().contains(NULL_VALUE_IN_STRING), "List should include  NULL_VALUE_IN STRING"),
                () -> assertEquals(Optional.empty(), emptyList, "Optional.empty() returns if the list is empty"),
                () -> assertEquals(Optional.empty(), nullList, "Optional.empty() returns if the list NULL")
        );
    }
    @Test
    @DisplayName("test function find user by email")
    void findUserByEmailTest(){
        Optional<User> userListBob =  findUserByEmail(USER_LIST,"bob@test.com");
        Optional<User> emptyEmail = findUserByEmail(USER_LIST, String.valueOf(Optional.empty()));
        Optional<User> emptyList = findUserByEmail(EMPTY_LIST, "bob@test.com");
        Optional<User> emptyListEmptyEmail = findUserByEmail(EMPTY_LIST, String.valueOf(Optional.empty()));
        Optional<User> nullList = findUserByEmail(NULL_USER_LIST,"bob@test.com");
        Optional<User> nullListEmptyEmail = findUserByEmail(NULL_USER_LIST,"bob@test.com");
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

}
