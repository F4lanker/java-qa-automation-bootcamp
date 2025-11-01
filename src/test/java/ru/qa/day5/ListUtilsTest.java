package ru.qa.day5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.*;
import static testData.ListSamplesForTests.*;

public class ListUtilsTest {

    @DisplayName("Positive case where list consists the element")
    @Test
    void containFunctHasInList(){
        assertAll(
                ()-> assertTrue(ListUtils.containsList(TEN_ELEMENTS, "Paris")),
                ()-> assertTrue(ListUtils.containsList(TEN_ELEMENTS, "b@test.com")),
                ()-> assertTrue(ListUtils.containsList(ONE_ELEMENT_LIST, "alfa")),
                ()-> assertTrue(ListUtils.containsList(REPEAT_ELEMENTS, "Sample")),
                ()-> assertTrue(ListUtils.containsList(NULL_AND_OTHER_DATA_LIST, null)),
                ()-> assertTrue(ListUtils.containsList(NULL_AND_OTHER_DATA_LIST, "Alice"))
        );
    }

    @DisplayName("Contains list function test with Empty list")
    @Test
    void containFuncEmptyListTest(){
        assertAll(
                ()-> assertFalse(ListUtils.containsList(EMPTIED_LIST, "Dave")),
                ()-> assertFalse(ListUtils.containsList(EMPTIED_LIST, "")),
                ()-> assertFalse(ListUtils.containsList(EMPTIED_LIST, null))
        );
    }

    @Test
    @DisplayName("Contains function test with null list")
    void containFuncNullListTest(){
        assertAll(
                ()-> assertFalse(ListUtils.containsList(NULL_LIST, "Boris")),
                ()-> assertFalse(ListUtils.containsList(NULL_LIST, "")),
                ()-> assertFalse(ListUtils.containsList(NULL_LIST, null))
        );
    }

    @Test
    @DisplayName("Contains function test with list contains null")
    void containFuncListContainsNull(){
        assertAll(
                ()-> assertFalse(ListUtils.containsList(NULL_CONTAIN_LIST, "Alice")),
                ()-> assertFalse(ListUtils.containsList(NULL_CONTAIN_LIST, "")),
                ()-> assertTrue(ListUtils.containsList(NULL_CONTAIN_LIST, null))
        );
    }
}
