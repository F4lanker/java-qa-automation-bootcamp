package ru.qa.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for /todos endpoint from jsonplaceholder API.
 * Represents a single todo item with completion status.
 *
 * @NoArgsConstructor required for Jackson deserialization:
 * Jackson creates object via default constructor, then populates fields via setters.
 */
@Data
@NoArgsConstructor // required for Jackson lib deserialization
public class TodoDto {
    private int id;
    private int userId;
    private String title;
    private boolean completed;
}
