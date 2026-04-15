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
1. Какой вариант лучше? - Я более менее разобрался с абстрактными классами, с интерфейсами ранее еще не взаимодействовал, надо в будушем закрыть этот пробел, лучше через практику. Логически кажется верным использование интерфейса, если не планируется хранить состояние и логику. 
2. Что если нужно хранить поля (requestSpec)? Если нужно хранить поля, то нужно использовать абстрактный класс
3. Что если нужен конструктор? Если нужен конструктор, то наш выбор тоже абстрактный класс. Конструкторы отсутсвуют в интерфейсах.

Сценарий 2: Контракт "все классы должны иметь метод validate()"
Требования:

Классы User, Order, Product должны иметь validate()
Нет общей реализации
Нет общих полей

Варианты:
A) Abstract class:
javapublic abstract class Validatable {
public abstract boolean validate();
}
B) Interface:
javapublic interface Validatable {
boolean validate();
}
Вопросы:

1. Какой вариант лучше? Если все классы долнжы обязательно иметь валидацию, то нам подойдет абстрактны класс. Он делает наследуюмый метод обязательным для реализации в классе наследнике.
2. Что если User уже наследуется от другого класса? Класс может наследоваться только от одного родительского класса. Тогда нужно будет implement interface. Для интерфейса достумно множественное применение.

Сценарий 3: Логирование для тестов
Варианты:
A) Abstract class:
javapublic abstract class LoggableTest extends BaseTest {
protected Logger logger = new Logger(...);
}
B) Interface:
javapublic interface Loggable {
default Logger getLogger() {
return new Logger(this.getClass().getName());
}
}
Вопросы:

Какой вариант гибче? - здесь затрудняюсь ответить. Видимо нужно более детальноеп погружение в интерфейсы
Можно ли комбинировать? Можно наследоваться от абстсрактного класса и применять интерфейсы.
