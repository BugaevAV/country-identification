# Определение страны по номеру телефона

### Инструкция по сборке и запуску проекта

1. Создать базу данных и запустить СУБД:   
```bash
docker-compose up -d
```

2. Открыть СУБД [country codes](http://localhost:8081/)
  

3. Запустить проект: `./gradlew run`
  

4. Открыть [поиск стран по номеру телефона](http://localhost:8088/)  


5. Запустить тесты `./gradlew test openJacocoReport` и  
    перейти в браузер для получения отчета
