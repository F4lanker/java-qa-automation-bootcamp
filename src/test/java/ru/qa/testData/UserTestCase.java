package ru.qa.testData;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserTestCase {
    private String scenario;
    private String name;
    private String email;
    private int expectedStatus;

    @Override
    public String toString() {
        return scenario;
    }
}
