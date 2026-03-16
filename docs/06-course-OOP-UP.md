# Java QA Automation Bootcamp — Обновлённая программа с ООП

> Обновлено: Март 2026
> Студент: @F4lanker
> Ментор: Claude

---

## 🎯 Структура курса (обновлённая)

| Блок | Дни | Статус |
|------|-----|--------|
| **Java Core + ООП Fundamentals** | 1-9 | ✅ Завершён |
| **JUnit5 & RestAssured Basics** | 10-13 | ✅ Завершён |
| **OOP Deep Dive** | 14-17 | 🆕 **Вставка** |
| **API Testing (RestAssured)** | 18-35 | ⏳ Планируется |
| **UI Testing** | 36-50 | ⏳ Планируется |
| **Kotlin + Kaspresso** | 51-65 | ⏳ Планируется |
| **CI/CD** | 66-75 | ⏳ Планируется |

---

## 🆕 Блок: OOP Deep Dive (Дни 14-17)

**Цель:** Заполнить пробелы в понимании ООП, модификаторов доступа, наследования и абстракции.

**Формат:**
- Теория с примерами из реального кода автотестов
- Практические задания на рефакторинг существующего кода
- Проектирование архитектуры тест-фреймворка

---

### **День 14 — Модификаторы доступа (Access Modifiers)**

#### **Теория:**

1. **`public`** — доступен везде
2. **`protected`** — доступен в пакете + наследникам
3. **package-private** (без модификатора) — только в пакете
4. **`private`** — только внутри класса

**Таблица видимости:**

| Модификатор | Класс | Пакет | Подкласс | Весь мир |
|-------------|-------|-------|----------|----------|
| public | ✅ | ✅ | ✅ | ✅ |
| protected | ✅ | ✅ | ✅ | ❌ |
| package-private | ✅ | ✅ | ❌ | ❌ |
| private | ✅ | ❌ | ❌ | ❌ |

#### **Практика:**

**Задание 14.1:** Анализ существующего кода

Проанализируй `BaseApiTest`:
- Почему `requestSpec` — `protected`, а не `private`?
- Что будет если сделать `setup()` `private`?
- Когда использовать `public static final`?

**Задание 14.2:** Рефакторинг ApiConfig

```java
// Текущий ApiConfig
public class ApiConfig {
    public static final String BASE_URL = "...";
}
```

Добавь:
- `private` конструктор (запрет создания экземпляра)
- JavaDoc объясняющий почему класс утилитный
- Метод `getBaseUrl()` — зачем? когда?

**Задание 14.3:** Создай иерархию конфигов

Создай структуру:
```
BaseConfig (abstract)
├── ApiConfig (public constants)
└── DbConfig (protected methods for tests)
```

Примени правильные модификаторы для каждого поля/метода.

**Задание 14.4:** Code Review Challenge

Дан код с неправильными модификаторами — найди и исправь:

```java
public class UserDto {
    public int id;  // ❌ Должно быть private
    protected String name;  // ❌ Зачем protected в DTO?
    private String getEmail() { return email; }  // ❌ Геттер private?
}
```

---

### **День 15 — Наследование (Inheritance)**

#### **Теория:**

1. **Наследование vs Композиция**
   - Когда `extends`
   - Когда создавать объект класса

2. **Отношение IS-A vs HAS-A**
   - `Dog extends Animal` (IS-A) ✅
   - `Car has Engine` (HAS-A) ✅

3. **`super` keyword**
   - Вызов конструктора родителя
   - Вызов методов родителя

4. **`@Override` аннотация**
   - Защита от опечаток
   - Явное переопределение

#### **Практика:**

**Задание 15.1:** Создай иерархию тестов

```
BaseTest (abstract)
├── BaseApiTest (abstract) — для API тестов
│   ├── GetRequestsTest
│   └── PostPutDeleteTest
└── BaseUiTest (abstract) — для UI тестов (заготовка)
    └── LoginPageTest (заготовка)
```

**Задание 15.2:** Переопределение методов

Создай:
```java
public abstract class BaseTest {
    @BeforeEach
    void setUp() {
        System.out.println("BaseTest setUp");
    }
}

public abstract class BaseApiTest extends BaseTest {
    @Override
    void setUp() {
        super.setUp();  // ✅ Вызов родительского
        System.out.println("BaseApiTest setUp");
        // Инициализация requestSpec
    }
}
```

Проверь порядок вызовов.

**Задание 15.3:** Наследование vs Композиция

Реализуй два подхода для `LoggingHelper`:

**Вариант A (наследование):**
```java
public class PostPutDeleteTest extends BaseApiTest {
    // Как добавить логирование?
}
```

**Вариант B (композиция):**
```java
public class PostPutDeleteTest extends BaseApiTest {
    private Logger logger = new Logger();

    @Test
    void test() {
        logger.info("Starting test");
    }
}
```

Какой лучше и почему?

**Задание 15.4:** Множественное "наследование" через композицию

Создай класс который использует:
- `BaseApiTest` (наследование)
- `TestDataBuilder` (композиция)
- `ReportHelper` (композиция)

---

### **День 16 — Абстрактные классы и методы**

#### **Теория:**

1. **Abstract class**
   - Нельзя создать экземпляр
   - Может содержать конкретные методы
   - Может иметь поля и конструкторы

2. **Abstract methods**
   - Без реализации (без тела)
   - Наследники обязаны реализовать
   - Только в abstract классах

3. **Когда использовать**
   - Общее поведение + обязательные части
   - Template Method pattern

#### **Практика:**

**Задание 16.1:** Добавь абстрактные методы в BaseApiTest

```java
public abstract class BaseApiTest {
    protected RequestSpecification requestSpec;

    // ✅ Абстрактный — каждый тест указывает свой путь
    protected abstract String getBasePath();

    @BeforeEach
    void setup() {
        requestSpec = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setBasePath(getBasePath())  // ✅ Вызов
            .build();
    }
}
```

Реализуй в `UserApiTest` и `PostApiTest`.

**Задание 16.2:** Template Method Pattern

Создай:
```java
public abstract class BaseEndToEndTest {
    @Test
    void endToEndScenario() {
        prepareTestData();   // ✅ Абстрактный
        executeActions();    // ✅ Абстрактный
        verifyResults();     // ✅ Абстрактный
        cleanup();           // ✅ Конкретный (общий для всех)
    }

    protected abstract void prepareTestData();
    protected abstract void executeActions();
    protected abstract void verifyResults();

    protected void cleanup() {
        // Общая логика очистки
    }
}
```

Реализуй `UserRegistrationE2ETest` и `OrderCreationE2ETest`.

**Задание 16.3:** Abstract vs Interface

Реализуй:

**Abstract class:**
```java
public abstract class BasePageObject {
    protected WebDriver driver;  // ✅ Поле

    public BasePageObject(WebDriver driver) {  // ✅ Конструктор
        this.driver = driver;
    }

    protected abstract void waitForPageLoad();  // ✅ Абстрактный метод
}
```

**Interface:**
```java
public interface Testable {
    void runTest();
    String getTestName();
}
```

Объясни когда что использовать.

---

### **День 17 — Полиморфизм и Static**

#### **Теория:**

1. **Полиморфизм**
   - Один интерфейс — разные реализации
   - Динамическое связывание

2. **Static members**
   - `static` поля — общие для всех экземпляров
   - `static` методы — вызов без объекта
   - Когда использовать

3. **`final` keyword**
   - `final` класс — нельзя наследовать
   - `final` метод — нельзя переопределить
   - `final` переменная — константа

#### **Практика:**

**Задание 17.1:** Полиморфизм в действии

```java
List<BaseApiTest> allTests = List.of(
    new UserApiTest(),
    new PostApiTest(),
    new TodoApiTest()
);

for (BaseApiTest test : allTests) {
    test.setup();  // ✅ Вызовется метод из конкретного класса
    System.out.println(test.getBasePath());
}
```

**Задание 17.2:** Static утилиты

Создай `TestUtils`:
```java
public final class TestUtils {  // ✅ final — нельзя наследовать
    private TestUtils() {  // ✅ private — нельзя создать
        throw new AssertionError("Utility class");
    }

    public static String generateRandomEmail() {
        return "test" + System.currentTimeMillis() + "@test.com";
    }

    public static int generateRandomId() {
        return new Random().nextInt(1000);
    }
}
```

Используй в тестах.

**Задание 17.3:** Constants класс

```java
public final class TestConstants {
    private TestConstants() { }

    public static final int DEFAULT_TIMEOUT = 5000;
    public static final String VALID_EMAIL = "test@test.com";

    public static final class Endpoints {
        public static final String USERS = "/users";
        public static final String POSTS = "/posts";
    }
}
```

Используй вложенные классы для группировки.

**Задание 17.4:** Экзамен по ООП

**Спроектируй архитектуру:**

```
BaseTest (abstract)
├── BaseApiTest (abstract)
│   ├── методы: setup(), tearDown()
│   ├── поля: requestSpec
│   └── абстрактные: getBasePath()
├── BaseUiTest (abstract)
│   ├── методы: setup(), tearDown()
│   ├── поля: driver
│   └── абстрактные: getPageUrl()
└── BaseDatabaseTest (abstract)
    ├── методы: setup(), tearDown()
    ├── поля: connection
    └── абстрактные: getTableName()
```

Реализуй по одному тесту для каждого типа.

Используй:
- ✅ Правильные модификаторы доступа
- ✅ Абстрактные методы где нужно
- ✅ Static утилиты где уместно
- ✅ Наследование vs композицию осознанно

---

## 🔄 Обновлённый порядок дней

| День | Тема | Статус |
|------|------|--------|
| 1-9 | Java Core (завершён) | ✅ |
| 10-13 | RestAssured Basics + BaseTest | ✅ |
| **14** | **Модификаторы доступа** | 🆕 |
| **15** | **Наследование** | 🆕 |
| **16** | **Абстрактные классы/методы** | 🆕 |
| **17** | **Полиморфизм + Static + Экзамен ООП** | 🆕 |
| 18-20 | RestAssured Advanced (было 14-16) | ⏳ |
| 21-25 | Query Params, Headers, Auth | ⏳ |
| 26-30 | Allure, Lombok Advanced, Frameworks | ⏳ |
| ... | ... | ... |

---

## 📋 Чеклист готовности к дням 14-17

**Перед началом блока ООП убедись что:**

- [ ] Завершён день 13 (BaseApiTest работает)
- [ ] Все тесты наследуются от BaseApiTest
- [ ] Понимаешь зачем `protected requestSpec`
- [ ] Готов анализировать и переписывать код

**После завершения блока ООП ты будешь:**

- ✅ Понимать когда какой модификатор использовать
- ✅ Различать наследование и композицию
- ✅ Применять абстрактные классы осознанно
- ✅ Проектировать тест-фреймворки самостоятельно

---

## 🎓 Материалы для самостоятельного изучения

**Книги:**
- "Effective Java" (Joshua Bloch) — глава 4 (Классы и интерфейсы)
- "Head First Java" — главы 7-8 (Наследование и полиморфизм)

**Видео:**
- "Java OOP Concepts" — FreeCodeCamp (YouTube)
- "Access Modifiers Explained" — Programming with Mosh

**Практика:**
- LeetCode OOP Design problems
- Refactoring.Guru — Design Patterns

---

*Обновлено: Март 2026*