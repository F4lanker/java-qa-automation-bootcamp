package ru.qa.day3;

import org.junit.jupiter.api.Test;
import ru.qa.constansts.UserRole;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRoleTest {

    @Test
    void shouldReturnAccessLvlCode(){
        assertAll(
                () -> assertEquals(1, AccessLvl.getAccessLevel(UserRole.GUEST)),
                () -> assertEquals(5, AccessLvl.getAccessLevel(UserRole.USER)),
                () -> assertEquals(10, AccessLvl.getAccessLevel(UserRole.ADMIN))

        );
    }
}
