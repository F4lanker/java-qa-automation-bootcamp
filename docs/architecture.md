# Архитектура проекта

## Структура директорий

### src/main/java/
Production код — может использоваться независимо от тестов.

- `config/` — константы и конфигурация
- `client/` — API клиенты
- `dto/` — Data Transfer Objects
- `util/` — утилиты
- `pages/` — Page Objects для UI

### src/test/java/
Тестовый код — зависит от main.

- `base/` — базовые классы для тестов
- `dayN/` — конкретные тесты
- `e2e/` — end-to-end тесты
- `testData/` — тестовые данные

## Правила
- main НЕ зависит от test
- test ЗАВИСИТ от main
- Все константы в `config/`