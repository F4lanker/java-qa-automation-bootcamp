Сценарий 1: Общая логика setup для всех тестов
Требования:

Все тесты должны иметь @BeforeEach setup()
Каждый тест добавляет свою логику

Варианты:
A) Abstract class:
javapublic abstract class BaseTest {
@BeforeEach
public void setup() {
System.out.println("Common setup");
}
}
B) Interface (Java 8+):
javapublic interface TestSetup {
default void setup() {
System.out.println("Common setup");
}
}
Вопросы:
1. Какой вариант лучше?
2. Что если нужно хранить поля (requestSpec)?
3. Что если нужен конструктор?
