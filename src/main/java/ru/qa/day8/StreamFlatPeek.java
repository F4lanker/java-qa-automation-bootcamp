package ru.qa.day8;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import ru.qa.day4.User;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class StreamFlatPeek {
    @Contract(" -> fail")
    private StreamFlatPeek(){throw new AssertionError("Нельзя создавать пустой экземляр утилитного класса");}

    // return the flat list of email from (List of List of Users)
    @Contract("null -> _; _ -> _")
    @NotNull
    public static @Unmodifiable List<String> flattenUserEmails(List<List<User>> userGroups){
        return Optional.ofNullable(userGroups)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)                               // filter lists
                .flatMap(user -> user.stream()
                        .filter(Objects::nonNull)                       //filter null users
                        .map(User::getEmail)
                        .filter(Objects::nonNull))                     //filter null in Emails
                .toList();
    }

}

