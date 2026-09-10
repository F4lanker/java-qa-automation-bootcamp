package ru.qa.day31.authTest;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.qa.base.AuthProviderRestfulBooker;

public class BasicAuthRestfulBookerTest extends AuthProviderRestfulBooker {
    @Test
    @DisplayName("Valid  credentials auth")
    @Epic("Restfull-booker")
    @Feature("Day 31  - Mini project API tests")
    @Story("Auth attempt return reason - Bad credentials, but status code - 200")
    @Severity(SeverityLevel.BLOCKER)
    void basicAuthTest(){

    }
}
