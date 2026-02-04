package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.qa.day4.User;
import ru.qa.day9.util.JsonUtils;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        User user = new User();
        user.setId(1);
        user.setName("Alice");
        user.setEmail("alice@test.com");
        user.setAge(25);

        String json = JsonUtils.toJson(user);
        System.out.println(json);
// → {"id":1,"name":"Alice","email":"alice@test.com","age":25}
        }
    }
