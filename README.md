Бэкенд-приложение для управления приютами и животными с базовой аналитикой на основе AI.

## Основные возможности

### Для всех пользователей (без авторизации):
- Просмотр списка приютов
- Детальная информация о приютах
- Поиск животных по приютам
- Профили животных с:
  - Текстовым описанием
  - Статистикой (анализ AI)

### Для сотрудников приютов (требуется авторизация):
- Редактирование информации о приюте
- Управление профилями животных
- Загрузка новых животных с фото/видео
- Модерация контента

## 🛠 Технологии
- **Backend**: Java 17, Spring Boot 3
- **База данных**: PostgreSQL
- **Видеоаналитика**: Python AI-сервис (интеграция через REST)
- **Авторизация**: JWT-токены
