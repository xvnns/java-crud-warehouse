# CRUD-приложение для управления товарами на складе

## Технологии и инструменты

- Spring Boot
- Hibernate
- Swagger
- Lombok
- JUnit, Mockito
- PostgreSQL

## Основные возможности

- Добавление, удаление, обновление и просмотр товаров на складе
- Глобальный обработчик исключений
- Валидация входных данных
- Аннотация, которая замеряет время работы метода (Spring AOP)
- Шедулер изменения цены товара (настраивается с помощью Conditional annotations)
- Многокритериальный поиск (Criteria API, полиморфная десериализация)

Для удобства работы с API в проект добавлена [коллекция Postman](https://github.com/xvnns/java-crud-warehouse/blob/main/docs/Warehouse.postman_collection.json) с готовыми запросами и тестами.
