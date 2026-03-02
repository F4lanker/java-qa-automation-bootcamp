package ru.qa.day3;

import ru.qa.constansts.UserRole;

public class AccessLvl {
    public static int getAccessLevel(UserRole role){
        return switch (role){
            case GUEST -> 1;
            case USER -> 5;
            case ADMIN -> 10;
        };
    }
}
