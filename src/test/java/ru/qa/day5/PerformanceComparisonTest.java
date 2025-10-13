package ru.qa.day5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.utils.measureTime;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PerformanceComparisonTest {
    @Test
    @DisplayName("LinkedList should be faster than ArrayList when adding to the beginning")
    void linkedList_AddToBeginning_IsFaster() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long arrayListTime = measureTime.measureTime(() -> {
            for (int i = 0; i < 10_000; i++) { // меньше, чтобы не зависало
                arrayList.add(0, i); // в начало
            }
        });

        long linkedListTime = measureTime.measureTime(() -> {
            for (int i = 0; i < 10_000; i++) {
                linkedList.add(0, i);
            }
        });

        System.out.println("ArrayList (start): " + arrayListTime / 1_000_000 + " ms");
        System.out.println("LinkedList (start): " + linkedListTime / 1_000_000 + " ms");

        assertTrue(linkedListTime < arrayListTime,
                "LinkedList должен быть быстрее при добавлении в начало");
    }
}
