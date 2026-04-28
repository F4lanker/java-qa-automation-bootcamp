Задание 20.1 — Найди дублирование в своих тестах
Создай документ: docs/day20-dry-analysis.md
Проанализируй свои тесты:

1. Найди дублирующийся код в тестах дней 18-19
2. Запиши примеры дублирования
3. Предложи решение через RequestSpec/ResponseSpec

Ответы. 

 Я стараюсь использовать .spec(requestSpec) с тех пор как мы его написали, поэтому задача не тривиальная. Понимаю, что есть опрделенный баланс, между новящевой идеей совершенно не иметь никакого дублирующегося кода и читаемостью\адевкатностью кода.
Итак. Поэтому давай покажу, что смутило меня в последних тестах, потом поищу пути оптимизации из задание.
 
Здесь дважды запшашиваем и сохраняем полостью идентичный posts. Но не уверен, что такие места можно оптимизировать  
public class PojoDeserializationTest extends BaseApiTest{
    @DisplayName("GET /posts/[id] - title is NOT null and id is according to requested")
    @Test
    public void shouldDeserializePostToPojo() {
    int id = 1;

        PostDto posts = given()
                .spec(requestSpec)
                .when()
                .get(getBasePath() + "/" + id)
                .then()
                .extract().as(PostDto.class);

        assertEquals(id, posts.getId());
        assertNotNull(posts.getTitle());
    }

    @Test
    @DisplayName("GET /posts/[id] - validates all fields via deserialization")
    public void shouldValidateAllFields() {
    int id = 1;
    PostDto posts = given()
    .spec(requestSpec)
    .when()
    .get(getBasePath() + "/" + id)
    .then()
    .extract().as(PostDto.class);
    
    assertAll(
    ()-> assertNotNull(posts.getId()),
    ()->assertNotNull(posts.getUserId()),
    ()->assertFalse(posts.getTitle().isEmpty()),
    ()->assertFalse(posts.getBody().isEmpty())
    ); } }


Что можно улучшить?
Проверку статус кода и логирование responses можно было бы использовать в последних тестах, чего я не дела ни в одно тесте дня 18-19. 
Если будут повторяющиеся заголовки, а не уникальные как в тестах HeadersTest то тоже можно добавтить в requestSpec