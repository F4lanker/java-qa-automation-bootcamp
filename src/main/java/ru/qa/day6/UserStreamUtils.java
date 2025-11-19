package ru.qa.day6;

import org.jetbrains.annotations.Contract;
import ru.qa.day4.User;

import java.util.*;


public final class UserStreamUtils {
    @Contract(" -> fail")
    private UserStreamUtils() {
        throw new AssertionError("Нельзя создавать пустой экземляр утилитного класса");
    }

    //return list of users with age over 18
    @Contract("null -> !null")
    public static Optional<List<User>> filterAdults(List<User> users) {
       if(users==null || users.isEmpty())
           return Optional.empty();
        return Optional.of(users.stream()
                .filter(Objects::nonNull)
                .filter(user -> user.getAge() >= 18).toList());
    }

    //    returns the first user from the list with email
    @Contract("null, _ -> !null; !null, null -> !null; !null, !null -> !null")
    public static Optional<User> findUserByEmail(List<User> users, String email) {
        if (users == null || email == null) {
            return Optional.empty();
        }
        return users.stream()
                .filter(Objects::nonNull)
                .filter(user -> Objects.equals(email, user.getEmail()))
                .findFirst();
    }

    //    returns the list of Names
    @Contract("_ -> !null")
    public static List<String> extractNames(List<User> users) {
        if (users == null) {
            return Collections.emptyList();
        }
        return users.stream()
                .filter(Objects::nonNull)
                .map(User::getName)
                .toList();
    }

    //    returns true, if found at least one user over inserted age
    @Contract("null, _ -> false")
    public static boolean hasUserWithAgeOver(List<User> users, int age) {
        if (users == null) {
            return false;
        }
        return users.stream()
                .filter(Objects::nonNull)
                .anyMatch(user -> user.getAge() >= age);
    }

    //first user Name which name starts with prefix
    @Contract("null, _ -> !null; !null, null -> !null; !null, !null -> !null")
    public static Optional<String> findFirstByNameStartingWith(List<User> users, String prefix) {
        if (users == null || prefix == null) {
            return Optional.empty();
        }
        return users.stream()
                .filter(Objects::nonNull)
                .map(User::getName)
                .filter(name -> name != null && name.startsWith(prefix))
                .findFirst();
    }

    // find user with min ID
    @Contract("null -> !null")
    public static Optional<User> getUserWithMinId(List<User> users) {
        if (users == null || users.isEmpty()) {
            return Optional.empty();
        }
        return users.stream()
                .min(Comparator.comparing(User::getId));
    }

    // find user with maximal aqe
    @Contract("null -> !null")
    public static Optional<User> getUserWithMaxAge(List<User> users){
        if(users == null || users.isEmpty()){
            return Optional.empty();
        }
        return users.stream()
                .max(Comparator.comparing(User::getAge));
    }
}
