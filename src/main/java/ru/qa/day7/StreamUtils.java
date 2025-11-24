package ru.qa.day7;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StreamUtils {
    @Contract(" -> fail")
    private StreamUtils(){ throw new AssertionError("Нельзя создавать пустой экземляр утилитного класса");
    }
    @Contract("null -> _; _ -> _")
    public static @NotNull @Unmodifiable List<String> toUpperCase(List<String> words){
        if(words == null || words.isEmpty()){
            return Collections.emptyList();
        }
        return words.stream()
                .filter(Objects::nonNull)
                .map(String::toUpperCase)
                .collect(Collectors.toCollection(HashSet::new))
                .stream().toList();
    }
}
