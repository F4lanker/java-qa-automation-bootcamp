package ru.qa.day7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static ru.qa.day7.StreamUtils.toUpperCase;
import static testData.ListSamplesForTests.*;

public class StreamUtilsTest {
    @DisplayName("Function returns List of inserted string in UPPER CASE")
    @Test
    void toUpperCaseTest(){
        Set<String> tenElementsResult = Set.of("SAMPLE", "STRING", "ALICE", "A@TEST.COM"," ", "BOB", "B@TEST.COM", "REFIX", "INDEX", "PARIS");
        Set<String> tenElementsActual = new HashSet<>(toUpperCase(TEN_ELEMENTS));
        Set<String> nullAndOtherElementsResult = Set.of("ALICE");
        Set<String> nullAndOtherElementsActual = new HashSet<>(toUpperCase(NULL_AND_OTHER_DATA_LIST));

        assertAll(
                ()-> assertEquals(tenElementsResult, tenElementsActual),
                ()-> assertEquals(Collections.emptyList(), toUpperCase(NULL_LIST)),
                ()-> assertEquals(Collections.emptyList(), toUpperCase(EMPTIED_LIST)),
                ()-> assertEquals(nullAndOtherElementsResult, nullAndOtherElementsActual),
                ()-> assertTrue(toUpperCase(REPEAT_ELEMENTS).contains("SAMPLE")),
                ()-> assertEquals(1, toUpperCase(REPEAT_ELEMENTS).size())
        );
    }
}
