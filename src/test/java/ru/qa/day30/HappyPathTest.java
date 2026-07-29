package ru.qa.day30;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;


import static ru.qa.base.WireMockExt.wm;

public class HappyPath extends WireMock {
    @Test
    void happyPath() {
        wm.stubFor(get(urlEqualTo("/users/1"))
                           .willReturn(aResponse()
                                               .withStatus(200)
                                               .withHeader("Content-Type", "application/json")
                                               .withBody("{\"id\":1,\"name\":\"Test User\"}")));
    }
}
