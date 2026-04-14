package ru.qa.base;

import org.junit.jupiter.api.BeforeEach;

/**
 * Root base class for ALL tests (API, UI, DB, etc.)
 * Provides common setup and teardown logic.
 */

public abstract class BaseTest {
    @BeforeEach
    public void setUpBase() {
        System.out.println("[BaseTest] Common setup for all tests");
        // Здесь можно добавить:
        // - Логирование
        // - Инициализацию test data
        // - Очистку временных файлов
    }
}
