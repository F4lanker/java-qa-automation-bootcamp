package ru.qa.day1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.day3.StatusMessageGenerator;

import static org.junit.jupiter.api.Assertions.*;

public class StatusMessageGeneratorTest {

    @DisplayName("Positive case with expected status code and IF realisation")
    @Test
    void expectedValuesTestForIF()
    {
     assertAll(
             () -> assertEquals("OK", StatusMessageGenerator.getStatusMessageIfVersion(200)),
             () -> assertEquals("Not Found", StatusMessageGenerator.getStatusMessageIfVersion(404)),
             () -> assertEquals("Bad Request", StatusMessageGenerator.getStatusMessageIfVersion(404)),
             () -> assertEquals("Server Error", StatusMessageGenerator.getStatusMessageIfVersion(500)),

     );
    }
@DisplayName("Test for integer out of the range IF realisation")
@Test
    void unknownValuesTestForIf(){
        assertAll(
                () -> assertEquals("Unknown", StatusMessageGenerator.getStatusMessageIfVersion(1)),
                () -> assertEquals("Unknown", StatusMessageGenerator.getStatusMessageIfVersion(Integer.MAX_VALUE)),
                () -> assertEquals("Unknown", StatusMessageGenerator.getStatusMessageIfVersion(-Integer.MAX_VALUE)),
                () -> assertEquals("Unknown", StatusMessageGenerator.getStatusMessageIfVersion(0))
        );


    }
}
