package testData;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserTestCase {
    private String name;
    private String email;
    private int expectedStatus;
}
