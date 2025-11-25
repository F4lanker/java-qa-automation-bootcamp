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
    private StreamUtils(){ throw new AssertionError("Нельзя создавать пустой экземляр утилитного класса");
    }
    @Contract("null -> _; _ -> _")
    @NotNull
    public static @Unmodifiable List<String> toUpperCase(@Nullable List<String> words){
        if(words == null || words.isEmpty()){
            return Collections.emptyList();
        }
        return words.stream()
                .filter(Objects::nonNull)
                .map(String::toUpperCase)
                .collect(Collectors.toCollection(HashSet::new))
                .stream().toList();
    }

    @Contract("null -> !null")
    public static Map<Integer, List<User>> groupByAge(List<User> users){
        if(users == null || users.isEmpty()){
            return Collections.emptyMap();
        }
        return users.stream()
                .collect(Collectors.groupingBy(User::getAge));
    }
}
