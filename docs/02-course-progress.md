# Java QA Automation Bootcamp — Прогресс

> Последнее обновление: май 2026
> Автор: @F4lanker

---

## 👤 Профиль студента

- **Опыт:** 5 лет Lead Manual QA Mobile
- **Цель:** Переход Manual QA → Mobile/Web SDET (Java → Kotlin/Kaspresso)
- **Темп:** 4-5 занятий в неделю по 1-1.5 часа
- **GitHub:** https://github.com/F4lanker/java-qa-automation-bootcamp

---

## 🗺️ Статус блоков

| Блок | Статус |
|------|--------|
| **1. Java Core (дни 1-9)** | ✅ Завершён |
| **2. ООП углубление (дни 14-18)** | ✅ Завершён |
| **3. RestAssured Basic (дни 10-13)** | ✅ Завершён |
| **4. RestAssured Advanced (дни 18-22)** | ✅ Завершён |
| **5. RestAssured Auth & Workflow (дни 23-35)** | 🔄 В процессе (день 23) |
| **6. UI Testing (Selenium/Selenide)** | ⏳ |
| **7. CI/CD** | ⏳ |

---

## ✅ Завершённые дни

### Блок 1: Java Core
- [x] День 1: Переменные, типы данных, VariablesDemo.java
- [x] День 2: JUnit5, первые тесты
- [x] День 3: Условные операторы (if/switch), StatusMessageGenerator
- [x] День 4: Классы, инкапсуляция, User.java
- [x] День 5: Массивы, списки, утилиты
- [x] День 6: Stream API (filter, map, collect)
- [x] День 7: Stream API (groupBy, partition, statistics), StreamUtils.java
- [x] День 8: flatMap, peek, кастомные коллекторы, StreamFlatPeek.java
- [x] День 9: Jackson (сериализация/десериализация), JsonUtils.java

### Блок 2: ООП углубление
- [x] День 14: Модификаторы доступа (анализ + рефакторинг)
- [x] День 15: Наследование, IS-A vs HAS-A, Logger (композиция)
- [x] День 16: Абстрактные классы, Template Method Pattern
- [x] День 17: Полиморфизм (overloading, overriding)
- [x] День 18: Интерфейсы, default методы, множественное наследование

### Блок 3: RestAssured Basic
- [x] День 10: GET запросы, ApiConfig, GetRequestsTest
- [x] День 11: POJO десериализация, TodoDto, UserDto
- [x] День 12: POST/PUT/DELETE, CreatePostRequest, UpdatePostRequest
- [x] День 13: BaseApiTest, RequestSpec/ResponseSpec, централизация

### Блок 4: RestAssured Advanced
- [x] День 18: Query параметры, headers, Builder Pattern (ApiRequestBuilder)
- [x] День 19: Path параметры, POJO десериализация (PostDto), SOLID SRP
- [x] День 20: DRY принцип, ApiSpecs, PostDtoAssertions
- [x] День 21: CRUD Workflow, User+Posts Workflow, негативные тесты
- [x] День 22: Негативные тесты (4xx), @ParameterizedTest, notFoundResponseSpec

---

## 🔄 Текущий день: 23 — Аутентификация

**Ветка:** `day/23-authentication`

**Задачи:**
- [ ] BasicAuthTest.java (httpbin.org)
- [ ] BearerTokenTest.java (reqres.in)
- [ ] BaseAuthApiTest.java (@BeforeAll токен)
- [ ] AuthorizedUserTest.java

---

## 🏗️ Архитектура проекта

### Ключевые классы (src/main):
```
ru.qa.config.ApiConfig          — BASE_URL, HTTPBIN_URL, REQRES_URL
ru.qa.specs.ApiSpecs            — baseRequestSpec(), authRequestSpec(), successResponseSpec()...
ru.qa.dto.*                     — PostDto, UserDto, CreatePostRequest, UpdatePostRequest
ru.qa.assertions.PostDtoAssertions — assertValidPost(), assertUserPost()
ru.qa.builder.ApiRequestBuilder — Builder Pattern для запросов
ru.qa.pages.BasePage            — Page Object заготовка
```

### Ключевые классы (src/test):
```
ru.qa.base.BaseTest             — abstract, @BeforeEach common setup
ru.qa.base.BaseApiTest          — extends BaseTest, requestSpec/responseSpec
ru.qa.base.BaseUiTest           — extends BaseTest, заготовка
ru.qa.base.BaseEndToEndTest     — Template Method Pattern
```

### Принятые решения:
- `getBasePath()` **убран** из BaseApiTest (слишком ограничивает)
- `ApiSpecs` используется через **static imports**
- `@Nested` для организации позитивных/негативных тестов
- Workflow тесты **не наследуются** от BaseApiTest (свобода endpoints)
- `ApiSpecs.baseRequestSpec(String baseUri)` — перегруженный метод для других API

---

## 💬 Ключевые договорённости с ментором

### Стиль работы:
- Ты пишешь код **сам**, ментор только задаёт задачи и делает ревью
- Ревью **объективное** — без приукрашивания
- ООП принципы объясняются **попутно** на примерах твоего кода
- Используй уже написанные утилиты (не дублируй)

### Best practices:
- `public final class + private constructor` для утилит
- `@Override` всегда при переопределении
- `super.method()` первым делом при переопределении
- `assertAll` для групповых проверок
- Сообщения в assertions: `assertEquals(5, size, "Should return 5 posts")`
- `Map.of()` вместо `new HashMap()` для неизменяемых map
- Given-When-Then порядок в RestAssured (headers/params в `given()`, HTTP метод в `when()`)
- Static imports для `ApiSpecs`, `Assertions`, `Matchers`

### Известные ограничения:
- jsonplaceholder — fake API, POST не сохраняет данные (id=101 не существует реально)
- Для CRUD workflow использовать id 1-100 (существующие)
- Для Basic Auth — httpbin.org
- Для Bearer Token — reqres.in

---

## 📝 Что улучшить (технический долг)

- [ ] Исправить `shouldReturn404ForNonExistentUserPosts` — противоречие в названии и проверке
- [ ] Добавить `/` перед endpoint в NegativePostsTest
- [ ] Добавить static imports в NegativePostsTest
- [ ] Рефакторинг ApiConfig — добавить HTTPBIN_URL, REQRES_URL
- [ ] Обновить `day16-abstract-vs-interface.md` после изучения интерфейсов

---

## 💡 Ключевые инсайты по ходу курса

1. **`static`** = принадлежит классу, а не экземпляру
2. **`final class`** = нельзя наследоваться (для утилит)
3. **IS-A** → наследование, **HAS-A** → композиция
4. **Abstract class** → когда нужны поля + реализация
5. **Interface** → когда только контракт, множественное наследование
6. **Template Method** → алгоритм в родителе, шаги в наследниках
7. **DRY** → RequestSpec/ResponseSpec убирают дублирование
8. **Builder Pattern** → для сложных объектов с опциональными параметрами
9. **SRP** → один класс = одна ответственность
10. **`@BeforeAll`** → одноразовая инициализация (токен), **`@BeforeEach`** → каждый тест