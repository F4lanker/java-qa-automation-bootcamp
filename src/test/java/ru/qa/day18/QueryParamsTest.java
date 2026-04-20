package ru.qa.day18;

import org.junit.jupiter.api.Test;
import ru.qa.base.BaseApiTest;

public class QueryParamsTest extends BaseApiTest {



    @Test
    public void shouldFilterPostsByUserId() {

    }

    @Override
    protected String getBasePath() {
        return "/posts";
    }
}
