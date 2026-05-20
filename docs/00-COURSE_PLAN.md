# Java QA Automation Bootcamp — Учебный план

> Актуальная версия: май 2026
> Текущий день: **23**

---

## 🎯 Цель курса

Перейти с Manual QA Lead → **Middle/Senior QA Automation Engineer (Java)**
Параллельная цель: освоить Kotlin/Kaspresso для Mobile SDET позиций.

---

## 📚 Полный план по дням

### ✅ Блок 1: Java Core (Дни 1-9) — ЗАВЕРШЁН

| День | Тема | Статус |
|------|------|--------|
| 1 | Переменные, примитивы, VariablesDemo | ✅ |
| 2 | JUnit5, первые тесты, NumberClassifier | ✅ |
| 3 | Условные операторы, StatusMessageGenerator, enum | ✅ |
| 4 | Классы, инкапсуляция, User.java | ✅ |
| 5 | Массивы, List, утилиты | ✅ |
| 6 | Stream API: filter, map, collect, Optional | ✅ |
| 7 | Stream API: groupBy, partition, statistics | ✅ |
| 8 | flatMap, peek, кастомные коллекторы | ✅ |
| 9 | Jackson: сериализация/десериализация JSON | ✅ |

---

### ✅ Блок 2: ООП углубление (Дни 14-18) — ЗАВЕРШЁН

| День | Тема | Статус |
|------|------|--------|
| 14 | Модификаторы доступа (анализ + рефакторинг) | ✅ |
| 15 | Наследование, IS-A vs HAS-A, композиция | ✅ |
| 16 | Абстрактные классы, Template Method Pattern | ✅ |
| 17 | Полиморфизм (overloading vs overriding) | ✅ |
| 18 | Интерфейсы, default методы | ✅ |

---

### ✅ Блок 3: RestAssured Basic (Дни 10-13) — ЗАВЕРШЁН

| День | Тема | Статус |
|------|------|--------|
| 10 | GET запросы, ApiConfig, BaseApiTest | ✅ |
| 11 | POJO десериализация, TodoDto, UserDto | ✅ |
| 12 | POST/PUT/DELETE, CreatePostRequest | ✅ |
| 13 | RequestSpec/ResponseSpec, BaseApiTest v2 | ✅ |

---

### ✅ Блок 4: RestAssured Advanced (Дни 18-22) — ЗАВЕРШЁН

| День | Тема | Статус |
|------|------|--------|
| 18 | Query params, headers, Builder Pattern | ✅ |
| 19 | Path params, POJO десериализация, SOLID SRP | ✅ |
| 20 | DRY принцип, ApiSpecs, PostDtoAssertions | ✅ |
| 21 | CRUD Workflow, User+Posts Workflow | ✅ |
| 22 | Негативные тесты (4xx), @ParameterizedTest | ✅ |

---

### 🔄 Блок 5: RestAssured Auth & Advanced (Дни 23-35) — В ПРОЦЕССЕ

| День | Тема | Статус | ООП/Принцип |
|------|------|--------|-------------|
| **23** | **Basic Auth, Bearer Token, @BeforeAll** | 🔄 **ТЕКУЩИЙ** | Strategy Pattern |
| 24 | Cookies, Sessions, stateful тесты | ⏳ | — |
| 25 | Загрузка/скачивание файлов | ⏳ | — |
| 26 | JSON Schema validation | ⏳ | Contract testing |
| 27 | Фильтры, кастомное логирование | ⏳ | Decorator Pattern |
| 28 | Allure интеграция, отчёты | ⏳ | — |
| 29 | Data-Driven тесты (CSV, JSON файлы) | ⏳ | — |
| 30 | WireMock — мок-сервер | ⏳ | Test isolation |
| 31-35 | **Мини-проект:** полное покрытие Swagger API | ⏳ | Все паттерны |

---

### ⏳ Блок 6: UI Testing (Дни 36-50)

| День | Тема |
|------|------|
| 36 | Selenium WebDriver, базовые методы |
| 37 | WebElement, структура тестов |
| 38 | Page Object Pattern |
| 39 | Selenide — обзор и преимущества |
| 40 | Переписываем тесты на Selenide |
| 41 | Скачивание/загрузка файлов (Selenium) |
| 42 | Alert, Basic Auth в UI |
| 43 | Iframe, Actions |
| 44 | JavascriptExecutor |
| 45 | Параллельный запуск тестов |
| 46 | Авторизация в UI через API |
| 47 | Screenshot тесты |
| 48 | Мобильная вёрстка |
| 49 | Чтение PDF/XLSX в тестах |
| 50 | **Мини-проект:** полное UI покрытие |

---

### ⏳ Блок 7: Kotlin + Kaspresso (Дни 51-65)

| День | Тема |
|------|------|
| 51 | Kotlin основы для Java-разработчика |
| 52 | Kotlin: data classes, extensions, lambdas |
| 53 | Kaspresso: установка, первый тест |
| 54 | Kaspresso: Page Object для Android |
| 55 | Kaspresso: Interceptors |
| 56-65 | **Мини-проект:** Android автотесты |

---

### ⏳ Блок 8: CI/CD (Дни 66-75)

| День | Тема |
|------|------|
| 66 | GitHub Actions: первый pipeline |
| 67 | GitHub Actions: запуск тестов на PR |
| 68 | Docker basics: запуск тестов в контейнере |
| 69 | Jenkins: установка, базовый pipeline |
| 70 | Jenkins: параметризованный pipeline |
| 71 | GitLab CI: pipeline с отчётом |
| 72 | Selenoid: кроссбраузерное тестирование |
| 73-75 | **Мини-проект:** полный CI/CD pipeline |

---

## 🛠️ Стек технологий

### Используется сейчас:
- **Java 17** — основной язык
- **JUnit 5** — тестовый фреймворк
- **RestAssured** — API тестирование
- **Jackson** — JSON сериализация
- **Lombok** — @Data, @Builder
- **Gradle** — сборщик проекта
- **Git/GitHub** — версионирование

### Будет добавлено:
- **Allure** — отчёты (день 28)
- **WireMock** — mock сервер (день 30)
- **Selenium/Selenide** — UI тесты (день 36)
- **Kotlin** — язык (день 51)
- **Kaspresso** — Android тесты (день 53)
- **Docker** — контейнеризация (день 68)
- **Jenkins/GitLab CI** — CI/CD (день 69)

---

## 📋 Правила работы с ментором

### Формат занятий:
1. Ментор даёт задачи **без готового кода**
2. Студент пишет **самостоятельно**
3. Код отправляется через **PR на GitHub**
4. Ментор делает ревью **по diff в PR**
5. После исправлений — merge в main

### Оценка кода:
- Ревью **объективное** — ошибки указываются прямо
- Оценки по шкале X/10 за каждый файл
- ООП принципы объясняются **попутно**

### Git workflow:
```bash
git checkout -b day/23-authentication
# пишешь код
git add .
git commit -m "feat(api): day 23 - basic auth and bearer token"
git push origin day/23-authentication
# создаёшь PR на GitHub
# отправляешь ссылку на PR ментору
```

---

## 🔄 Как продолжить в новом чате

```
Привет! Продолжаем курс Java QA Automation.

GitHub: https://github.com/F4lanker/java-qa-automation-bootcamp
Контекст: docs/COURSE_PROGRESS.md и docs/COURSE_PLAN.md

Текущий день: 23 (Аутентификация — Basic Auth, Bearer Token)
PR с кодом: [ссылка на PR]

Пожалуйста:
1. Прочитай docs/COURSE_PROGRESS.md — там текущий статус
2. Сделай ревью PR
3. Продолжи курс с учётом правил в docs/course-rules.md
```

---

## 📊 Карьерная траектория

```
Сейчас (май 2026)
└── Lead Manual QA, изучаю автоматизацию (день 23 из 75)

Через 3 месяца
└── Завершить курс → Портфолио (API + UI + CI/CD)

Через 6 месяцев
└── Middle QA Automation Engineer ($2000-3000)

Через 12 месяцев
└── Senior Mobile SDET с Kaspresso ($3000-4500)

Через 18 месяцев
└── Релокация (EU/UAE) — если нужно
```