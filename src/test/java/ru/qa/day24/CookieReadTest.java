package ru.qa.day24;

import io.restassured.filter.cookie.CookieFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static io.restassured.config.RedirectConfig.redirectConfig;
import static org.hamcrest.Matchers.equalTo;
import static ru.qa.specs.ApiSpecs.*;

public class CookieReadTest {
    @Test
    @DisplayName("GET: Query params returned in cookies")
    void shouldReceiveCookieFromServer() {
        String sessionId = "abc123";
        given()
                .spec(httpBinRequestSpec())
                .queryParam("sessionId", sessionId)
                .config(config().redirect(redirectConfig().followRedirects(false))) //without redirect only read cookies is available
                .when()
                .get("/cookies/set")
                .then()
                .log().all()
                .cookie("sessionId", sessionId);
    }

    @Test
    @DisplayName("GET: baseURL/cookies returns sent cookie")
    void shouldSendCookieToServer(){
        String token = "mySecretToken123";
        given()
        .spec(httpBinRequestSpec())
                .cookie("token", token)
                .when()
                .get("/cookies")
        .then()
                .log().all()
                .body("cookies.token", equalTo(token));
    }

    @Test
    @DisplayName("Cookies saved across session of 2 requests. User name is in the body of second reply")
    void shouldMaintainSessionAcrossRequests(){
        CookieFilter cookieFilter = new CookieFilter(); // change from required SessionFilter to Cookie filter as Session can't read custom cookie value as user
        String user = "nikolai";

        given()
                .spec(httpBinRequestSpec())
                .queryParam("user", user)
                .filter(cookieFilter)
                .config(config().redirect(redirectConfig().followRedirects(false))) // have to switch off redirect to read cookies.  If redirects happened inside "given()...when()...then()" — cookie from passing 302 is missing.
                .log().all()
                .when()
                .get("/cookies/set")
                .then()
                .log().all();

        given()
                .spec(httpBinRequestSpec())
                .filter(cookieFilter)
                .when()
                .get("/cookies")
                .then()
                .body("cookies.user", equalTo(user));
    }
}
