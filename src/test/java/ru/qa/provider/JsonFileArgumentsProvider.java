package ru.qa.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.InputStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.stream.Stream;

public class JsonFileArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        // context знает, какой метод сейчас запускается
        // с этого метода читаем нашу аннотацию
        JsonSource annotation = context.getRequiredTestMethod()
                                       .getAnnotation(JsonSource.class);

        String path = annotation.path();       // "testdata/users.json"
        Class<?> type = annotation.type();     // UserTestCase.class

        InputStream is = getClass().getClassLoader().getResourceAsStream(path);
        ObjectMapper mapper = new ObjectMapper();
        Object[] cases = (Object[]) mapper.readValue(is,
                                                     mapper.getTypeFactory().constructArrayType(type));

        return Arrays.stream(cases).map(Arguments::of);
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @ArgumentsSource(JsonFileArgumentsProvider.class)
    public @interface JsonSource {
        String path();
        Class<?> type();
    }
}
