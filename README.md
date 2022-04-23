# vezdecod-java-3 Project

    Этот мкс для задания 3 java.
    Написан на quarkus.
    Используется postgres, hibernate, классический rest.
    Для удобства проверки и наглядности добавлен сваггер.

- [Текст задания](https://vk.com/@vezdekod-java-23181)
- [Github](https://github.com/i-wonderful/vezdecod-java-3)
- [Запущен на heroku](https://vezdecod-java-3.herokuapp.com)
- [SwaggerUI на heroku](https://vezdecod-java-3.herokuapp.com/swagger-ui/)


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
### Способ 3. Собрать нейтив сборку, подробности в оф доке



     Почему quarkus? Потому что он прекрасен и воплощает все лучшее в современной java разработке.
     Быстрый и удобный, умеет билдится в нейтив сборку.
     Активно развивается, с обширной документацией.
     Так почему бы его не заюзать и тут, ведь мы здесь собратись just for fun.

