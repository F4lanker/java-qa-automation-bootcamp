package ru.qa.base;

import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import ru.qa.util.RestfulBookerCredsConfig;
import ru.qa.specs.ApiSpecs;
import ru.qa.util.RestfulBookerAuthApi;

import static ru.qa.config.constants.ApiConfig.RESTFULBKR_URL;

public class AuthProviderRestfulBooker {

    protected static String accessToken;
    protected static RequestSpecification authSpec;


    @BeforeAll
    static void authenticate() {

        RestfulBookerCredsConfig rstCfg = ConfigFactory.create(RestfulBookerCredsConfig.class);
        String userName = rstCfg.rstBookerName();
        String password = rstCfg.rstBookerPass();

        accessToken = RestfulBookerAuthApi.authResponseRestulBooker(userName, password)
                                          .getBody().jsonPath().getString("token");

        Assertions.assertNotNull(accessToken); //check the token exists

        authSpec = ApiSpecs.authRequestSpecCookie(RESTFULBKR_URL, accessToken);
    }

}

