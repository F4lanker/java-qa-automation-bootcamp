// UserDto.java
public class UserDto {
public int id;                    // ❌ Поля для DTO не должны быть публитчными. Java beans контракт, доступны через методы, геттеры сеттеры и коннструкторы
protected String name;            // ❌ Здесь та же самая проблема, поле дожно быть private, с protected можно будет получить доступ из наследников и из классов пакета
String email;                     // ⚠️ не указан модиикатор, будет использован модификатор по умолчанию, а именно package private, по доступности это лучше чем все что было выше, но все таки остается доступ из пакета. Используем только private

    private String getEmail() {       // ❌  getter  виден только внутри класса с private чтобы к геттеру был доступ вне класса, он должен быть публичным, JavaBeans
        return email;
    }

    protected void setId(int id) {    // ❌ Та же проблема что выше. Можжно будет полчить доступ из пакета и из наследников, но внешние пакеты не смогут использовать конструктор
        this.id = id;
    }
}

// TestHelper.java
class TestHelper {                    // ⚠️ модификатора доступа у класса нет, значит по умолчанию будет package-rivate. Если класс используется только внутри пакета то ОК. Если планируется использование вовне то надо моенять на public
public static String formatDate() {
return "2025-01-01";
}
}

// BaseApiTest.java
public abstract class BaseApiTest {
public RequestSpecification requestSpec;  // здесь подходит модификатор protected. Если поставим Private то доступа в классах наследниках не будет. Public - код будет работать, но будет слишком открыт, любой вне пакета может сломать.

    private void setup() {             // я бы поставил тоже protected, немного колебалсф в коде который ты предлагал был public, но думаю  если переменная requestSpec с модификатором protected, но логично использовать его и здесь
        requestSpec = ...;
    }
}