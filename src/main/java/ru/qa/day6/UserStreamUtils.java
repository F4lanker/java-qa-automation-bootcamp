package ru.qa.day6;

import ru.qa.day4.User;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public final class UserStreamUtils {
    private UserStreamUtils() {
        throw new AssertionError("Нельзя создавать пустой экземляр утилитного класса");
    }

    //return list of users with age over 18
    public static Optional<List<User>> filterAdults(List<User> users) {
       if(users==null || users.isEmpty())
           return Optional.empty();
        return Optional.of(users.stream()
                .filter(Objects::nonNull)
                .filter(user -> user.getAge() >= 18).toList());
    }

    //    returns the first user from the list with email
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
    public static Optional<Object> extractNames(List<User> users) {
        if (users == null) {
            return Optional.empty();
        }
        return Optional.of(users.stream()
                .map(User::getName)
                .toList());
    }

    //    returns true, if found at least one user over inserted age
    public static boolean hasUserWithAgeOver(List<User> users, int age) {
        if (users == null) {
            return false;
        }
        return users.stream()
                .anyMatch(user -> user.getAge() > age);
    }

    //first user Name which name starts with prefix
    public static Optional<String> findFirstByNameStartingWith(List<User> users, String prefix) {
        if (users == null || prefix == null) {
            return Optional.empty();
        }
        Optional<String> prefixName = users.stream()
                .map(User::getName)
                .filter(name -> name != null && name.startsWith(prefix))
                .findFirst();
        return prefixName;
    }

    // find user with min ID
    public static Optional<User> getUserWithMinId(List<User> users) {
        if (users == null || users.isEmpty()) {
            return Optional.empty();
        }
        return users.stream()
                .min(Comparator.comparing(User::getId));
    }

    // find user with maximal aqe
    public static Optional<User> getUserWithMaxAge(List<User> users){
        if(users == null || users.isEmpty()){
            return Optional.empty();
        }
        return users.stream()
                .max(Comparator.comparing(User::getAge));
    }
}
