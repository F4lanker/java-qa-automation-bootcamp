package ru.qa.day5;

import ru.qa.day4.User;

import java.util.*;
import java.util.stream.Collectors;

public final class UserListUtils {
        private UserListUtils() {}

        public static List<User> filterAdults(List<User> users) {
            if (users == null) return new ArrayList<>();
            return users.stream()
                    .filter(user -> user.getAge() >= 18)
                    .collect(Collectors.toList());
        }

        public static User findUserByEmail(List<User> users, String email) {
            if (users == null || email == null) return null;
            return users.stream()
                    .filter(user -> email.equals(user.getEmail()))
                    .findFirst()
                    .orElse(null);
        }

        public static boolean hasDuplicates(List<User> users) {
            if (users == null) return false;
            Set<User> set = new HashSet<>(users);
            return set.size() < users.size();
        }

        public static List<User> sortByName(List<User> users) {
            if (users == null) return new ArrayList<>();
            List<User> sorted = new ArrayList<>(users);
            sorted.sort(Comparator.comparing(User::getName));
            return sorted;
        }
    }


