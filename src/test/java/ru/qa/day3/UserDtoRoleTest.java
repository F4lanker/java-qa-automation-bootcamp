package ru.qa.day3;

import org.junit.jupiter.api.Test;
import ru.qa.config.constansts.UserRole;
import ru.qa.days.day3.AccessLvl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDtoRoleTest {

    @Test
    void shouldReturnAccessLvlCode(){
        assertAll(
                () -> assertEquals(1, AccessLvl.getAccessLevel(UserRole.GUEST)),
                () -> assertEquals(5, AccessLvl.getAccessLevel(UserRole.USER)),
                () -> assertEquals(10, AccessLvl.getAccessLevel(UserRole.ADMIN))

        );
    }
}
