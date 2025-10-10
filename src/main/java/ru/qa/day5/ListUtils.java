package ru.qa.day5;

import java.util.List;

public final class ListUtils {

    private ListUtils(){
        throw new AssertionError("Нельзя создавать экземпляр утилитного класса");
    }
    public static boolean containsList(List<String> list, String name){
        if(list == null) {
            return false;
        }
        return list.contains(name);
    }

    public static List<String> addToList (List<String> list, String value){
//        if(list == null) {
//            throw  new IllegalArgumentException("List can NOT be null");
//        } in case of empty list reutrn the list
        list.add(value);
       return list;
    }


    public static List<String> removeOnIndex(List<String> list, int index){
//        if(list == null) {
//            throw  new IllegalArgumentException("List can NOT be null");
//        }
//        if (list.isEmpty()){
//            throw new IllegalArgumentException("List can NOT be empty");
//        } in case of empty list reutrn the list
        if (index < 0){
            throw new IllegalArgumentException("Index should be more than 0");
        }
        if (index >= list.size()) {
            throw new IllegalArgumentException("Index should be more than 0");
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
