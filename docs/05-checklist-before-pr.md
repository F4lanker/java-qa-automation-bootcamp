### ✅ Чеклист перед отправкой PR

- [ ] Все тесты проходят (`./gradlew test`)
- [ ] Код соответствует `Актуальным правилам`
- [ ] Нет `System.out.println()` в production-коде
- [ ] Использованы `final class` + `private constructor` для утилит
- [ ] Обработка `null` — согласно политике
- [ ] Сообщение коммита начинается с `feat:`, `fix:`, `docs:` и т.д.
- [ ] В описании PR указаны: цель, файлы, что можно проверять

### Шаблон отправки задания

📌 Последний коммит: [краткое описание изменений]
🔍 Файлы, которые нужно проверить: ArrayUtils.java, ListUtils.java
✅ Что исправлено:
- Удалена проверка isEmpty()
- Исправлены сообщения об ошибках
- Добавлен String.format() в IndexOutOfBoundsException


Реализованы:
- filter, map, findFirst
- min/max с Comparator
- Optional в поиске

Файлы: UserStreamTest.java, StreamUtils.java

Готов к ревью.