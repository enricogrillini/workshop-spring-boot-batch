# Spring-boot-rest-api

Batch base basato su ApplicationRunner

Utilizzo con java -jar:
```shell
mvn clean package

# Genera eccezione
java -jar ./target/application-runner.jar --exception

# Stampa variabili
java -jar ./target/application-runner.jar --print --first-argument=first-value --second-argument=second-value third-argument
```

Utilizzo con spring-boot:run:

```shell
# Genera eccezione
mvn clean spring-boot:run "-Dspring-boot.run.arguments=--exception"

# Stampa variabili
mvn clean spring-boot:run "-Dspring-boot.run.arguments=--print --first-argument=first-value --second-argument=second-value third-argumen"

```