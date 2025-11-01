package ru.qa.day5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static testData.ListSamplesForTests.*;

public class ListUtilsTest {

    @DisplayName("Positive case where list consists the element")
    @Test
    void containFucntHasInList(){
        assertAll(
                ()-> assertEquals(true,ListUtils.containsList(tenElements, "Paris")),
                ()-> assertEquals(true,ListUtils.containsList(tenElements, "b@test.com")),
                ()-> assertEquals(true,ListUtils.containsList(oneElementList, "alfa")),
                ()-> assertEquals(true,ListUtils.containsList(repeatElements, "Sample")),
                ()-> assertEquals(true,ListUtils.containsList(nullAndOtherDataList, null)),
                ()-> assertEquals(true,ListUtils.containsList(nullAndOtherDataList,"Alice"))
        );
    }

    @DisplayName("Contains list function test with Empty list")
    @Test
    void containFuncEmptyListTest(){
        assertAll(
                ()-> assertEquals(false, ListUtils.containsList(emptyList, "Dave")),
                ()-> assertEquals(false, ListUtils.containsList(emptyList, "")),
                ()-> assertEquals(false, ListUtils.containsList(emptyList, null))
        );
    }

    @Test
    @DisplayName("Contains function test with null list")
    void containFuncNullListTest(){
        assertAll(
                ()-> assertEquals(false, ListUtils.containsList(nullList, "Boris")),
                ()-> assertEquals(false, ListUtils.containsList(nullList, "")),
                ()-> assertEquals(false, ListUtils.containsList(nullList, null))
        );
    }

    @Test
    @DisplayName("Contains function test with list contains null")
    void containFuncListContainsNull(){
        assertAll(
                ()-> assertEquals(false, ListUtils.containsList(nullContainList, "Alice") ),
                ()-> assertEquals(false, ListUtils.containsList(nullContainList, "") ),
                ()-> assertEquals(true, ListUtils.containsList(nullContainList, null) )
        );
    }
}
