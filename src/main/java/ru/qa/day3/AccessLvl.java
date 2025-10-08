package ru.qa.day3;

public class AccessLvl {
    public static int getAccessLevel(UserRole role){
        return switch (role){
            case GUEST -> 1;
            case USER -> 5;
            case ADMIN -> 10;
        };
    }
}
