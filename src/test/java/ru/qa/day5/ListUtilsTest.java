package ru.qa.day5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtilsTest {

    List<String> emptyList = new ArrayList<>();
    List<String> nullContainList = Arrays.asList((String) null);
    List<String> nullAndOtherDataList = Arrays.asList((String) null, "Alice");
    List<String> nullList = null;
    List<String> oneElementList = new ArrayList<String>(List.of("alfa"));
    List<String> tenElements = new ArrayList<String>(List.of("Sample", "String", "Alice", "a@test.com", " ", "Bob", "b@test.com", "refix", "Index", "Paris" ));
    List<String> repeatElements = new ArrayList<String>(List.of("Sample", "Sample", "Sample" ));


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
