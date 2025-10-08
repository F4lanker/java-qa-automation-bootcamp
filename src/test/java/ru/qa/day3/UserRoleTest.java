package ru.qa.day3;

import static org.junit.jupiter.api.Assertions.*;

public class UserRoleTest {
    void shouldReturnAccessLvlCode(){
        assertAll(
                () -> assertEquals(1, AccessLvl.getAccessLevel(UserRole.GUEST)),
                () -> assertEquals(5, AccessLvl.getAccessLevel(UserRole.USER)),
                () -> assertEquals(10, AccessLvl.getAccessLevel(UserRole.GUEST))

        );
    }
}
