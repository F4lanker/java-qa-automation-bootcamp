package ru.qa.day5;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.qa.day4.User;

import java.util.*;

public final class UserListUtils {
        @Contract(" -> fail")
        private UserListUtils() throws AssertionError {throw new AssertionError("Нельзя создавать пустой экземляр утилитного класса");
        }


        @Contract("null -> !null")
        public static Optional<List<User>> filterAdults(List<User> users) {
            if (users == null)
                return Optional.empty();
            List <User> result =  users.stream()
                    .filter(Objects::nonNull)
                    .filter(user -> user.getAge() >= 18)
                    .toList();
            return Optional.of(result);
        }

        @Contract("null, _ -> null; !null, null -> null")
        public static User findUserByEmail(List<User> users, String email) {
            if (users == null || email == null)
                return null;
            return users.stream()
                    .filter(user -> email.equals(user.getEmail()))
                    .findFirst()
                    .orElse(null);
        }

        @Contract("null -> false")
        public static boolean hasDuplicates(List<User> users) {
            if (users == null) return false;
            Set<User> set = new HashSet<>(users);
            return set.size() < users.size();
        }

        @Contract("null -> new")
        public static @NotNull List<User> sortByName(List<User> users) {
            if (users == null) return new ArrayList<>();
            List<User> sorted = new ArrayList<>(users);
            sorted.sort(Comparator.comparing(User::getName));
            return sorted;
        }
    }


