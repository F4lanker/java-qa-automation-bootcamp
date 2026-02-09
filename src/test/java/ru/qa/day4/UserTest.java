package ru.qa.day4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @DisplayName("Create a user")
    @Test
    void createUserTest(){
        User user = new User(1, "Bob", "bobcrs@gmail.com", 21);

        assertAll(
                () -> assertEquals(1, user.getId()),
                () -> assertEquals("Bob", user.getName()),
                () -> assertEquals("bobcrs@gmail.com", user.getEmail()),
                () -> assertEquals(21, user.getAge())
        );
    }

    @Test
    @DisplayName("Should be equal when same data")
    void shouldBeEqual_WhenSameData() {
        User user1 = new User(1, "Alice", "alice@test.com", 25);
        User user2 = new User(1, "Alice", "alice@test.com", 25);

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    @DisplayName("Should not be equal when different id")
    void shouldNotBeEqual_WhenDifferentId() {
        User user1 = new User(1, "Alice", "alice@test.com", 25);
        User user2 = new User(2, "Alice", "alice@test.com", 25);

        assertNotEquals(user1, user2);
    }

    @Test
    @DisplayName("Should not be equal to null")
    void shouldNotBeEqual_ToNull() {
        User user = new User(1, "Alice", "alice@test.com", 25);
        assertFalse(user.equals(null));
    }

    @Test
    @DisplayName("Should have readable toString")
    void shouldHaveReadableToString() {
        User user = new User(1, "Alice", "alice@test.com", 25);
        String expected = "User{id=1, name='Alice', email='alice@test.com', age=25}";
        assertEquals(expected, user.toString());
    }
}
