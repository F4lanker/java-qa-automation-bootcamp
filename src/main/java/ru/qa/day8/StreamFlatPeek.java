package ru.qa.day8;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import ru.qa.day4.User;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StreamFlatPeek {
    @Contract(" -> fail")
    private StreamFlatPeek(){throw new AssertionError("Нельзя создавать пустой экземляр утилитного класса");}

    @Contract("null -> _; _ -> _")
    @NotNull
    public static @Unmodifiable List<String> flattenUserEmails(List<List<User>> userGroups){
        if(userGroups == null || userGroups.isEmpty()){
            return Collections.emptyList();
        }
        return userGroups.stream()
                .filter(Objects::nonNull)
                .flatMap(user -> user.stream().map(User::getEmail))
                .toList();
    }

}

