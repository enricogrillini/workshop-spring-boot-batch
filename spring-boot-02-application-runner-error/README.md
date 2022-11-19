# Spring-boot-rest-api

Batch base basato su ApplicationRunner:

```shell
mvn clean package

java -jar ./target/application-runner-advanced.jar --print

mvn clean spring-boot:run "-Dspring-boot.run.arguments=--print"
```

Utilizzo batch:

```shell
# Stampa help
mvn clean spring-boot:run "-Dspring-boot.run.arguments=--help"

# Stampa versione
mvn clean spring-boot:run "-Dspring-boot.run.arguments=--version"

# Esecuzione: stampa variabili passate
mvn clean spring-boot:run "-Dspring-boot.run.arguments=--print"
```