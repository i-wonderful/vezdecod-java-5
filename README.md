# vezdecod-java-1 Project

Vezdecode Задание 1 java.
Мкс реализующий RestAPI. бд postgres.
Используется Resteasy, hibernate и др.

- [Текст задания](https://vk.com/@vezdekod-java-23181)
- [Github](https://github.com/i-wonderful/vezdecod-java-1)
- [Запущен на heroku](https://vezdecod-java-1.herokuapp.com)
- [SwaggerUI на heroku](https://vezdecod-java-1.herokuapp.com/swagger-ui/)

## Сборка и Запуск
### Способ 1. Запуск локально в dev режиме:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  в дев режиме работает интересный ui http://localhost:8080/q/dev/.

### Способ 2. Собрать jar и запустить его
```shell script
./mvnw package
java -jar target/quarkus-app/quarkus-run.jar
```

### Способ 3. Собрать нейтив сборку на graalVM
```shell script
./mvnw package -Pnative
```
Если нет graalVM можно попробовать через докер
```shell script

```
 В случае успеха соберется билд: `./target/vezdecod-java-1-1.0-SNAPSHOT-runner`
