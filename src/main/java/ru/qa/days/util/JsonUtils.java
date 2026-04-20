package ru.qa.days.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    static{
        // Убираем null-поля из JSON
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // Игнорируем поля в JSON, которых нет в классе
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    // Стандартная сериализация
    public static String toJson(Object object) throws JsonProcessingException {
        try {
            return mapper.writeValueAsString(object);
        } catch(JsonProcessingException e){
            throw new RuntimeException("Serialisation failed: ", e);
        }
    }

    // Серриализация через список
    public static <T> T fromJsonFileList(String jsonPath, TypeReference<T> typeReference) throws JsonProcessingException  {
        try {
            return mapper.readValue(new File(jsonPath), typeReference);
        } catch (Exception e){
            throw new RuntimeException("Deserialization failed for type: ", e);
        }
    }

    // Десериализация из Json файла
    public static <T> T fromJsonFile(String jsonPath, Class<T> out)  {
        try {
            return mapper.readValue(new File(jsonPath), out);
        } catch (Exception e){
            throw new RuntimeException("Deserialization failed: ", e);
        }
    }

    // Красивый вывод
    public static String toPrettyJson(Object object){
        try{
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(object);
        }catch(JsonProcessingException e){
            throw new RuntimeException("Pretty-serrialisation failed: ", e);
        }
    }
}
