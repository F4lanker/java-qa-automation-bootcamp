package ru.qa.day3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import ru.qa.constansts.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

public class StatusMessageGeneratorTest {

    @Nested
    @DisplayName("Test with first itteration method If and Swith case")
    class IfSwitchCases {
        @DisplayName("Positive case with expected status code and IF realisation")
        @Test
        void expectedValuesTestForIF() {
            assertAll(
                    () -> assertEquals("OK", StatusMessageGenerator.getStatusMessageIfVersion(200)),
                    () -> assertEquals("Not Found", StatusMessageGenerator.getStatusMessageIfVersion(404)),
                    () -> assertEquals("Bad Request", StatusMessageGenerator.getStatusMessageIfVersion(400)),
                    () -> assertEquals("Server Error", StatusMessageGenerator.getStatusMessageIfVersion(500))

            );
        }

        @DisplayName("Test for integer out of the range IF realisation")
        @Test
        void unknownValuesTestForIf() {
            assertAll(
                    () -> assertEquals("Unknown", StatusMessageGenerator.getStatusMessageIfVersion(1)),
                    () -> assertEquals("Unknown", StatusMessageGenerator.getStatusMessageIfVersion(Integer.MAX_VALUE)),
                    () -> assertEquals("Unknown", StatusMessageGenerator.getStatusMessageIfVersion(-Integer.MAX_VALUE)),
                    () -> assertEquals("Unknown", StatusMessageGenerator.getStatusMessageIfVersion(0))
            );
        }

        @DisplayName("Positive case with expected status code and SWITCH realosation")
        @Test
        void expectedValuesTestForSwitch() {
            assertAll(
                    () -> assertEquals("OK", StatusMessageGenerator.getStatusMessageSwitchCase(200)),
                    () -> assertEquals("Not Found", StatusMessageGenerator.getStatusMessageSwitchCase(404)),
                    () -> assertEquals("Bad Request", StatusMessageGenerator.getStatusMessageSwitchCase(400)),
                    () -> assertEquals("Server Error", StatusMessageGenerator.getStatusMessageSwitchCase(500))
            );
        }

        @DisplayName("Test for integer out of the range SWITCH realisation")
        @Test
        void unknownValuesTestForSwitch() {
            assertAll(
                    () -> assertEquals("Unknown", StatusMessageGenerator.getStatusMessageSwitchCase(1)),
                    () -> assertEquals("Unknown", StatusMessageGenerator.getStatusMessageSwitchCase(Integer.MAX_VALUE)),
                    () -> assertEquals("Unknown", StatusMessageGenerator.getStatusMessageSwitchCase(-Integer.MAX_VALUE)),
                    () -> assertEquals("Unknown", StatusMessageGenerator.getStatusMessageSwitchCase(0))
            );
        }
    }

    @Nested
    @DisplayName("Refactored method with Enum")
    class EnumHttpCode{

        @ParameterizedTest
        @EnumSource(HttpStatus.class)
        @DisplayName("All Statuses has it's own Messages")
        void allStatusShouldHaveMessage(HttpStatus status){
            assertNotNull(status.getMessage());
        }

        @ParameterizedTest
        @CsvSource({"200, OK", "400, Bad Request", "404, Not Found", "500, Server Error"})
        @DisplayName("Known codes returns correct message")
        void knownCodeReturnsCorrectStatus(int code, String name){
            assertEquals(name, HttpStatus.fromCode(code).getMessage());
        }
    }
}
