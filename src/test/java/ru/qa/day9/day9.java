package ru.qa.day9;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.qa.day4.User;
import ru.qa.day9.util.JsonUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static testData.UserSamples.TEENAGE_USER;
import static testData.UserSamples.VALID_USER;

public class day9 {
    @Nested
    @DisplayName("JsonUtils FromJsonFile test by Jackson")
    class FromJson{

        @Test
        @DisplayName("Simple data test one User in the file")
        void oneUserJsonFile(){
            String jsonPath = "src/main/resources/oneValidUser.json";
            User Stas = new User();
                Stas.setId(1);
                Stas.setName("Stas");
                Stas.setAge(18);
                Stas.setEmail("stas@mail.ru");

            assertEquals(JsonUtils.fromJsonFile(jsonPath, User.class), Stas);
        }

        @Test
        @DisplayName("Several users in the file")
        void fewUsersInJson() throws JsonProcessingException {
            String jsonPath = "src/main/resources/fewUserInFile.json";
            String result  = JsonUtils.toJson(List.of(VALID_USER, TEENAGE_USER));
            assertEquals(JsonUtils.fromJsonFile(jsonPath, User.class), result);
        }
// Do NOT show any null values
        @Test
        @DisplayName("Test of NULL data in JSON")
        void nullTest() throws JsonProcessingException {
            String jsonPath = "src/main/resources/nullValue.json";
            String result = String.valueOf(JsonUtils.fromJsonFile(jsonPath, User.class));
            //check that JSON doesnt contain any NULL values
            assertAll(
                    ()-> assertTrue(result.contains("id=3")),
                    ()-> assertTrue(result.contains("age=18")),
                    ()-> assertTrue(result.contains("name=null")),
                    ()-> assertTrue(result.contains("email=null"))
           );
        }
        @Test
        @DisplayName("Null file test")
        void nullFile(){
            //assertThrows(RuntimeException.class, ()-> JsonUtils.fromJsonFile(null, User.class));
            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    ()-> JsonUtils.fromJsonFile(null, null)
            );
                    assertEquals("Ошибка дессериализации", exception.getMessage());
        }
    }
}
