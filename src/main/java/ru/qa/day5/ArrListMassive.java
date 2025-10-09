package ru.qa.day5;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrListMassive {
    String[] names = {"Alice", "Bob", "Charlie", "Diana", "Eve"};
    public boolean containsNameArr( String[] names, String name){
        return containsNameArr(String[] names, name);
    }

    ArrayList<String> namesList = new ArrayList<>(Arrays.asList(names));
    public boolean containsNameArrList(String[] array, String name){
    return namesList.contains(name);
    }

    void addNamesList(String name) {
        namesList.add(name);
    }

    void removeOnIndex(int index){
        if (index < 0){
            throw new IllegalArgumentException("Index should be more than 0");
        }
        if (index >= namesList.size()) {
            throw new IllegalArgumentException("Index should be more than 0");
        }
        namesList.remove(index);
    }

    public String[] backToArray(){
        return (String[]) namesList.toArray();
    }
}
