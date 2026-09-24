# 📊 Руководство по работе с Allure Report через терминал

## 🎯 Цель
Быстрая генерация и просмотр отчётов о тестировании без GUI IDE.

---

## 🚀 Быстрый старт (2 команды)

### 1️⃣ Очистка предыдущих результатов
```bash
rm -rf allure-results
```

### 2️⃣ Запуск тестов и генерация отчёта
```bash
./gradlew clean test allureReport
```

---

## 📂 Доступные команды Allure

| Команда | Описание | Пример |
|---------|----------|--------|
| `allureReport` | Генерирует HTML отчёт | `./gradlew allureReport` |
| `allureServe` | Запускает локальный сервер + генерация | `./gradlew allureServe` |
| `clean` | Очищает build-артефакты (включая allure-results) | `./gradlew clean` |

---

## 🌐 Просмотр отчёта в браузере

### Вариант 1: Файловый путь (быстро)
```bash
open build/reports/allure-report/allureReport/index.html
# или на Mac:
open file:///Users/.../build/reports/allure-report/allureReport/index.html
```

### Вариант 2: Локальный сервер (рекомендуется для команды)
```bash
./gradlew allureServe
# Откроется http://localhost:5000 в браузере автоматически
```

---

## 📊 Структура отчёта

Отчёт содержит:
- ✅ **Тесты** — список всех тестов с статусом (PASSED/FAILED)
- ✅ **Скриншоты** — если были сделаны во время теста
- ✅ **Логирование** — вывод консоли из каждого теста
- ✅ **Шаги** — детализация по шагам в `@Step` аннотациях
- ✅ **Метки** — severity, epic, story и т.д.

---

## 🔧 Частые сценарии использования

### Сценарий 1: Полный цикл (очистка + тесты + отчёт)
```bash
./gradlew clean test allureReport
open build/reports/allure-report/allureReport/index.html
```

### Сценарий 2: Только генерация отчёта (без пересборки)
```bash
./gradlew allureReport
```

### Сценарий 3: Запуск с сервером для команды
```bash
./gradlew clean test allureServe
# Откроется http://localhost:5000
```

---

## 📁 Расположение файлов

| Путь | Описание |
|------|----------|
| `allure-results/` | Бинарные файлы результатов тестов (raw) |
| `build/reports/allure-report/` | Сгенерированный HTML отчёт |
| `index.html` | Главная страница отчёта |

---

## 🐛 Отладка проблем

### Проблема: "No tests found"
**Решение:** Убедитесь, что тесты имеют аннотацию `@Test`:
```java
@Test  // ← Обязательно!
void myTest() { ... }
```

### Проблема: "Allure results not found"
**Решение:** Очистите и перезапустите:
```bash
rm -rf allure-results
./gradlew clean test allureReport
```

---

## 📝 Примеры для CI/CD (GitHub Actions, GitLab CI)

### GitHub Actions
```yaml
- name: Run tests
  run: ./gradlew clean test

- name: Generate Allure report
  run: ./gradlew allureReport

- name: Upload Allure reports
  uses: actions/upload-artifact@v3
  with:
    name: allure-report
    path: build/reports/allure-report/
```

### Публикация на GitHub Pages
```bash
# После генерации отчёта:
git add build/reports/allure-report/
git commit -m "docs(allure): update test report"
git push origin main
```

---

## 🎓 Ключевые выводы

1. **`allureReport`** — генерирует HTML файл в `build/reports/allure-report/`
2. **`allureServe`** — запускает локальный сервер на `http://localhost:5000`
3. **Очистка** перед запуском: `rm -rf allure-results`
4. **Просмотр**: используйте `open` (Mac) или браузер для открытия HTML файла

---

## 📚 Дополнительные ресурсы

- [Allure Documentation](https://docs.qameta.io/allure/)
- [Gradle Allure Plugin](https://github.com/qameta/allure-gradle-plugin)
