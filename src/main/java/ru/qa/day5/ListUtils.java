package ru.qa.day5;

import java.util.List;
import java.util.Objects;

public final class ListUtils {

    private ListUtils(){
        throw new AssertionError("Нельзя создавать экземпляр утилитного класса");
    }
    public static boolean containsList(List<String> list, String name){
        if(list == null) {
            return false;
        }
        final boolean b = list.stream().anyMatch(s-> Objects.equals(s, name));
        return b;
    }

    public static List<String> addToList (List<String> list, String value){
        if(list == null) {
            throw  new IllegalArgumentException("List can NOT be null");
        }
        list.add(value);
       return list;
    }


    public static List<String> removeOnIndex(List<String> list, int index){
        if(list == null) {
            throw  new IllegalArgumentException("List can NOT be null");
        }
        if (index < 0){
            throw new IndexOutOfBoundsException("Index should be more than 0");
        }
        if (index >= list.size()) {
            throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for size %d", index, list.size()));
        }
        list.remove(index);

        return list;
    }

    public static String[] array(List<String> list){
        if(list == null) {
            throw  new IllegalArgumentException("List can NOT be null");
        }
        return list.toArray(new String[0]);
    }
}
