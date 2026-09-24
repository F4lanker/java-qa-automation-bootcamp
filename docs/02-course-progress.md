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

# Java QA Automation Bootcamp — Прогресс

> Последнее обновление: август 2026
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
| **5. RestAssured Auth & Workflow (дни 23-35)** | 🔄 В процессе (день 30 завершён) |
| **6. UI Testing (Selenium/Selenide)** | ⏳ |
| **7. CI/CD** | ⏳ |

---

## ✅ Завершённые дни — Блок 5 (детально)

- [x] День 23: Basic Auth, Bearer Token, @BeforeAll — `BaseAuthApiTest`, `ApiKeyConfig`
- [x] День 24: Cookies, Sessions — `CookieFilter`, `SessionFilter`, stateful workflow тесты
- [x] День 25: File Upload/Download — `multiPart()`, `ClassLoader`, `java.nio.file`
- [x] День 26: JSON Schema Validation — `SchemaValidatorUtil`, contract testing
- [x] День 27: Filters & Custom Logging — `CustomRequestLoggingFilter`, `TimingFilter`, DI
- [x] День 28: Allure Reports — `@Epic/@Feature/@Story`, `@Step`, `@Attachment`, `AllureRestAssured`
- [x] День 29: Data-Driven тесты — `@CsvFileSource`, `@MethodSource`, `@JsonSource` (кастомный `ArgumentsProvider`), Schema Validation интеграция
- [x] День 30: WireMock — `WireMockExt` (JUnit5 `@RegisterExtension`), happy path / error path / delay-timeout сценарии, переиспользование `TimingFilter` на моке

**Следующий день: 31 — старт мини-проекта (полное покрытие Swagger API, дни 31-35)**

---

## 🏗️ Архитектура проекта (актуально на день 30)

### Ключевые классы (src/main):
```
ru.qa.config.constants.ApiConfig     — BASE_URL, HTTPBIN_URL, REQRES_URL
ru.qa.config.constants.ApiKeyConfig  — Owner-интерфейс для API ключей
ru.qa.config.constants.AuthConfig    — учётные данные для авторизации
ru.qa.specs.ApiSpecs                  — baseRequestSpec(), baseRequestSpec(String baseUri), authRequestSpec()...
                                         + AllureRestAssured фильтр встроен в baseRequestSpec
ru.qa.filter.CustomRequestLoggingFilter — DI через PrintStream, гибкое логирование
ru.qa.filter.TimingFilter              — порог времени ответа, AssertionError при превышении;
                                         переиспользован на дне 30 для WireMock delay-тестов
ru.qa.util.SchemaValidatorUtil         — checkSchemaJson(), checkSavedJsonSchema()
ru.qa.days.util.JsonUtils              — ObjectMapper-обёртка, toJson()/fromJson()
ru.qa.dto.*                            — PostDto, UserDto, LoginDto, CreatePostRequest...
ru.qa.testData.UserTestCase            — DTO для happy-path параметризации (день 30)
ru.qa.testData.UserErrorTestCase       — DTO для error-path параметризации (день 30)
```

### Ключевые классы (src/test):
```
ru.qa.base.BaseTest              — abstract, @BeforeEach common setup
ru.qa.base.BaseApiTest           — extends BaseTest, requestSpec/responseSpec
ru.qa.base.BaseAuthApiTest       — @BeforeAll токен, ApiKeyConfig static final
ru.qa.base.WireMockExt           — abstract, @RegisterExtension WireMockExtension (день 30)
listener.RetryListener           — TestExecutionExceptionHandler, retry + failedTests.txt
ru.qa.provider.JsonFileArgumentsProvider — кастомный @JsonSource (день 29)
```

### Принятые архитектурные решения:
- `ApiSpecs` — `final class` + `private constructor`, методы через **static imports**
- `AllureRestAssured` фильтр встроен прямо в `baseRequestSpec()` — все запросы автоматически логируются
- DI через конструктор — паттерн для всех кастомных Filter-классов (`PrintStream output`, `TimingFilter(thresholdMs)`)
- `SchemaValidatorUtil` универсален — принимает либо path+spec, либо готовый response body
- `RetryListener` — кастомный JUnit5 extension, retry до 3 раз + запись упавших в файл
- Workflow/stateful тесты — без наследования от `BaseApiTest` (свобода endpoints)
- **WireMock-тесты наследуются от `WireMockExt`** — не через static import, потому что `@RegisterExtension` регистрируется JUnit5 только через иерархию класса
- Happy-path и error-path тесты разделены по DTO/JSON-файлам (`UserTestCase` vs `UserErrorTestCase`) — разные контракты ответа не должны проверяться одним и тем же ассертом
- Заглушки (`stubFor`) живут внутри тестового класса рядом с использующим их тестом — не в отдельном пакете; логически это часть Given-шага теста

---

## 💬 Ключевые договорённости с ментором

### Стиль работы:
- Студент пишет код **сам**, ментор только задаёт задачи и делает ревью
- Ревью **объективное** — без приукрашивания, с оценкой X/10 по файлам
- ООП принципы и паттерны объясняются **попутно** на примерах кода студента
- Используются уже написанные утилиты (не дублируются)
- Теория даётся ДО задания — отдельным блоком, с примерами и best practices
- **С дня 30**: при знакомстве с новым инструментом — по умолчанию предлагать разбивку на этапы и совместное прохождение документации, если тема физически новая (не переиспользование уже знакомых концептов)

### Allure аннотации — стандарт (с дня 28):
```java
@Test
@DisplayName("Short name for IDE")
@Epic("Java QA Bootcamp")              // всегда одно и то же
@Feature("Day N - Topic Name")         // день курса — для группировки в Behaviors view
@Story("Specific scenario name")
@Severity(SeverityLevel.CRITICAL)      // CRITICAL/NORMAL/MINOR/TRIVIAL/BLOCKER
@Description("Detailed explanation of what is verified and why")
void testMethod() { ... }
```

### Best practices (накопленные):
- `public final class + private constructor` для утилит
- `@Override` всегда при переопределении
- Static imports для `ApiSpecs`, `Assertions`, `Matchers` — но точечно там, где есть риск коллизии имён (см. инсайты дня 30)
- Given-When-Then порядок в RestAssured
- `Files.createDirectories()` — идемпотентно, безопасно для повторных запусков
- `ClassLoader.getSystemResource(...).toURI()` — для путей к тестовым ресурсам (не `.getFile()`)
- DI через конструктор для классов которым нужна внешняя зависимость (PrintStream и др.)
- Параметризация только когда структура теста одинакова — не используется когда ожидаемое поведение разное (pass vs exception)
- `allure-results/` нужно чистить перед прогоном (`./gradlew clean test`) — иначе старые тесты остаются в отчёте после переноса между классами
- Сериализация тестовых данных в JSON — только через `ObjectMapper`/`JsonUtils`, никогда через `Map.toString()` (день 30)
- Тайминг-тесты на малых порогах (десятки мс) без прогрева JVM/connection pool — источник flaky-тестов; масштаб порогов должен доминировать над инфраструктурным шумом

### Известные ограничения тестовых API:
- jsonplaceholder — fake API, POST не сохраняет данные
- httpbin.org — для Basic Auth и cookies/sessions
- reqres.in — для Bearer Token, требует `X-API-Key` header
- reqres.in не валидирует Bearer токен строго — тест должен включать header даже если API не форсирует проверку
- WireMock (localhost, день 30) — сервер живёт только на время класса (`@RegisterExtension` static → `BeforeAllCallback`/`AfterAllCallback`), стабы сбрасываются между тестами по умолчанию

---

## 📝 Технический долг / на будущее

- [ ] Привести `@Severity`/`@Description` к единому стилю во всех day28 тестах
- [ ] Решить — нужен ли `loggingRequestSpec()` теперь когда есть `AllureRestAssured` в base spec
- [ ] Добавить `clean` задачу в `build.gradle` для автоочистки `allure-results/`
- [ ] Рассмотреть вынос `@Epic`/`@Feature` на уровень класса (не дублировать в каждом методе)
- [ ] WireMock Scenarios/States — не пройдено, вернуться при необходимости многошаговых сценариев ("первый вызов успех, второй — ошибка")
- [ ] `.withBodyFile(...)` вместо inline JSON в стабах — когда данных станет больше
- [ ] WireMock Plugin в IntelliJ — установлен, использование отложено до появления большего числа стабов
- [ ] Reflection / Generics / ExtensionContext (долг с дня 29) — по-прежнему открыт, план: после дня 35

---

## 💡 Ключевые инсайты с дней 23-28

1. **CookieFilter vs SessionFilter** — CookieFilter хранит все cookies, SessionFilter только session-cookie
2. **Редирект и cookies** — при followRedirects(true) промежуточный Set-Cookie из 302 теряется; нужно followRedirects(false) чтобы CookieFilter успел перехватить
3. **ClassLoader + toURI()** — toURI() корректно декодирует спецсимволы (пробелы) в пути, getFile() — нет
4. **Files.createDirectories — идемпотентен**, createDirectory (без s) — бросает исключение при повторном вызове
5. **Dependency Injection** — разделение объявления зависимости (поле без значения) и её создания (передача через конструктор снаружи)
6. **DI ≠ Overloading** — Overloading про количество вариантов метода (compile-time), DI про то кто создаёт зависимость (архитектура)
7. **flush() vs close()** — flush выталкивает буфер, оставляя поток открытым; close = flush + освобождение ресурса
8. **try-with-resources** — гарантирует close() даже при исключении внутри блока
9. **Allure Behaviors view** — Epic→Feature→Story даёт осмысленную группировку вместо алфавитной по пакетам
10. **allure-results накопительна** — не очищается автоматически, при переносе тестов между классами старые результаты остаются и дублируются в отчёте

## 💡 Ключевые инсайты с дня 30 (WireMock)

1. **`@RegisterExtension` регистрируется только через иерархию класса** — static import поля `wm` компилируется, но JUnit5 находит extension только через `extends`; без наследования сервер физически не поднимается
2. **Похожие имена классов — не только методов** — `WireMock` (DSL) и `WireMockExt` (свой класс) достаточно похожи, чтобы обмануть автодополнение IDE при `extends`; при коллизиях помогает Cmd+клик — проверить откуда реально резолвится тип
3. **В Java нет локальных методов** — только локальные переменные и локальные классы; попытка объявить метод внутри метода — синтаксическая ошибка независимо от аннотаций
4. **Разные fluent-DSL нельзя склеивать точкой** — `wm.stubFor(...)` (WireMock) и `given()...when()...then()` (RestAssured) — независимые операторы с разными типами возврата, а не одна цепочка
5. **`Map.toString()` — не JSON** — `{key=value}` (дефолтный Object.toString) синтаксически невалиден как JSON (`{"key":"value"}`); для сериализации нужен `ObjectMapper`
6. **WireMock 404 "no stub mappings" ≠ "stub не совпал"** — первое означает, что на сервере вообще нет зарегистрированных стабов (проблема регистрации/лайфцикла), второе — что запрос не подошёл ни под один критерий (проблема matcher'а); тексты сообщений разные и точно указывают, что искать
7. **`TimingFilter` меряет весь HTTP-цикл, а не только искусственный delay** — TCP-хендшейк, холодный старт JVM/connection pool добавляют overhead поверх `withFixedDelay`; на малых порогах (~50мс) это доминирует и ломает тест
8. **`@ParameterizedTest(name=...)` плейсхолдеры `{0}`, `{1}`** — индексы параметров МЕТОДА, не полей объекта; для одного параметра-DTO валиден только `{0}`, который равен `toString()` этого объекта целиком
9. **AssertionError — unchecked** — не требует `throws` в сигнатуре метода, в отличие от `JsonProcessingException` (checked)
10. **`throws` наверх vs `@SneakyThrows`** — в тестовом коде предпочтителен явный `throws`: тестовый метод не является частью публичного API, которое нужно "защищать" от checked-исключений; `@SneakyThrows` уместнее в production-коде, где исключение практически недостижимо, но мешает сигнатуре интерфейса