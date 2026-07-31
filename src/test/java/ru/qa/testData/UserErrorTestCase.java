package ru.qa.testData;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserErrorTestCase {
    private int id;
    private String scenario;
    private int expectedStatus;
    private String error_message;
}
