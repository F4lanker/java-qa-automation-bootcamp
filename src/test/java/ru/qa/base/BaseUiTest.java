package ru.qa.base;

import org.junit.jupiter.api.BeforeEach;

public abstract class BaseUiTest extends BaseTest {
    @Override
    @BeforeEach
    public void setUpBase() {
        super.setUpBase();

        System.out.println("[BaseUiTest] UI-specific setup");
        // TODO: Инициализация WebDriver
        // driver = new ChromeDriver();
    }
}
