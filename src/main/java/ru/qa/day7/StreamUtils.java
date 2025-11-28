package ru.qa.day7;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import ru.qa.day4.User;

import java.util.*;
import java.util.stream.Collectors;

public class StreamUtils {
    @Contract(" -> fail")
    private StreamUtils() {
        throw new AssertionError("Нельзя создавать пустой экземляр утилитного класса");
    }

    // function returns User list in Upper Case removing duplicates
    @Contract("null -> _; _ -> _")
    @NotNull
    public static @Unmodifiable List<String> toUpperCase(@Nullable List<String> words) {
        if (words == null || words.isEmpty()) {
            return Collections.emptyList();
        }
        return words.stream()
                .filter(Objects::nonNull)
                .map(String::toUpperCase)
                .collect(Collectors.toCollection(HashSet::new))
                .stream().toList();
    }

    // function returns dictionary with users grouped by age
    @Contract("null -> !null")
    @NotNull
    public static Map<Integer, List<User>> groupByAge(List<User> users) {
        if (users == null || users.isEmpty()) {
            return Collections.emptyMap();
        }
        return users.stream()
                .collect(Collectors.groupingBy(User::getAge));
    }

    // function returns group of adults and non adults
    @Contract("null -> !null")
    @NotNull
    public static @Unmodifiable Map<Boolean, List<User>> partitionByAdultStatus(List<User> users) {
        if (users == null || users.isEmpty()) {
            return Collections.emptyMap();
        }
        return users.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.partitioningBy(
                        user -> user.getAge() >= 18));
    }

    // returns age statistics
    @Contract("null -> !null")
    @NotNull
    public static DoubleSummaryStatistics getAgeStatistics(List<User> users) {
        if (users == null || users.isEmpty()) {
            return new DoubleSummaryStatistics();
        }
        return users.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.summarizingDouble(User::getAge));
    }
}
