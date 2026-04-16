package ru.qa.days.day8;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import ru.qa.days.day4.User;

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
// debugging method
    public static void logProcessingSteps(@NotNull List<User> users){
        users.stream()
                .filter(Objects::nonNull)
                .filter(user -> user.getName() != null)
                .peek(user -> System.out.println("Обрабатываю: " + user.getName()))
                .filter(user -> user.getAge() >= 18)
                .peek(user -> System.out.println("Взрослый: " + user.getName()))
                .toList();
    }
    }



