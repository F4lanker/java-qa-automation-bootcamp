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
// debagging method
    public static void logProcessingSteps(@NotNull List<User> users){
        users.stream()
                .filter(Objects::nonNull)
                .filter(user -> user.getName() == null)
                .peek(user -> System.out.println("Обрабатываю: " + user.getName()))
                .filter(user -> user.getAge() >= 18)
                .peek(user -> System.out.println("Взрослый: " + user.getName()))
                .toList();
    }
// custom collector for collecting complicated statistics
//    @Contract(" -> !null")
//    public static @NotNull Collector<User, ?, UserSummary> toUserSummary(){
//        return Collector.of(
//                UserSummary::new,                            //Supplier: initial state
//                 (summary, user) -> summary.add(user), //Accumulator: add each user
//                (a, b) -> a.merge(b)                   //Combiner: add each user
//        );

    }



