package ru.qa.filter;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class RequestLoggingFilter implements Filter {
    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        long timStart = System.currentTimeMillis();

        // Logging method and URL
        System.out.println(requestSpec.getMethod() + requestSpec.getURI());

        // Передаём управление дальше — выполняется реальный запрос
        Response response = ctx.next(requestSpec, responseSpec);
        long timEnd = System.currentTimeMillis();

        String timeResponse = String.valueOf((timEnd - timStart));
        //Logging Status code and response time
        System.out.println(response.getStatusCode() + timeResponse); // здесь время надо померить

        return response; // возвращаем ответ в цепочку
    }
}
