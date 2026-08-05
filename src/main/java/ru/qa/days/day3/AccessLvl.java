package ru.qa.days.day3;

import ru.qa.config.constants.UserRole;

public class AccessLvl {
    public static int getAccessLevel(UserRole role){
        return switch (role){
            case GUEST -> 1;
            case USER -> 5;
            case ADMIN -> 10;
        };
    }
}
