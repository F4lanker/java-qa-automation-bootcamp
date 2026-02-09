package ru.qa.day3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
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
                    () -> assertEquals("Unknown", StatusMessageGenerator.getStatusMessageIfVersion(1)),
                    () -> assertEquals("Unknown", StatusMessageGenerator.getStatusMessageIfVersion(Integer.MAX_VALUE)),
                    () -> assertEquals("Unknown", StatusMessageGenerator.getStatusMessageIfVersion(-Integer.MAX_VALUE)),
                    () -> assertEquals("Unknown", StatusMessageGenerator.getStatusMessageIfVersion(0))
            );
        }
    }

    @Nested
    @DisplayName("Refacored method with Enum")
    class EnumHttpCode{

        @Test
        @DisplayName("Enum positive tests")
        void enumCodeTest(){
            assertAll(
                    ()-> assertEquals("OK", HttpStatus.OK.getMessage()),
                    ()-> assertEquals(200, HttpStatus.OK.getCode()),
                    ()-> assertEquals("Bad Request", HttpStatus.BAD_REQUEST.getMessage()),
                    ()-> assertEquals(400, HttpStatus.BAD_REQUEST.getCode()),
                    ()-> assertEquals("Not Found", HttpStatus.NOT_FOUND.getMessage()),
                    ()-> assertEquals(404, HttpStatus.NOT_FOUND.getCode()),
                    ()-> assertEquals("Server Error", HttpStatus.SERVER_ERROR.getMessage()),
                    ()-> assertEquals(500, HttpStatus.SERVER_ERROR.getCode())
            );
        }
        @Test
        @DisplayName("Negative tests for Enum")
        void negativeEnumTRests(){
            assertEquals(-1, HttpStatus.UNKNOWN.getCode());
            assertEquals("Unknown", HttpStatus.UNKNOWN.getMessage());
        }
    }
}
