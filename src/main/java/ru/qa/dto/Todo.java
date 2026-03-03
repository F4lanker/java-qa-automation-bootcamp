package ru.qa.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for serialization of https://jsonplaceholder.typicode.com/todos.
 * Used for RestAssured Auto-testing.
 */
@Data
@NoArgsConstructor // required for Jackson lib deserialization
public class Todo {
    private int id;
    private int userId;
    private String title;
    private boolean completed;
}
