1. Найди нарушения SRP:

Есть ли в твоём коде классы, которые делают больше одной вещи?
Ответ:
public class StreamUtils
public static @Unmodifiable List<String> toUpperCase(@Nullable List<String> words) //возвращает список капсом
public static Map<Integer, List<User>> groupByAge(List<User> users) // возвращает фильтрованные по возрасту словарь 

Улучшить можно выделив класс, который меняет данные. И класс, который возвращает статистику, пример фильтры, сортировки и тд.
В остальном все классы соответсвуют SRP, возможно что-то пропустил 
