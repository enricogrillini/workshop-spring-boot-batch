# Spring-boot-rest-api

Batch base basato su ApplicationRunner:

Utilizzo con java -jar:
```shell
mvn clean package

java -jar ./target/application-runner-advanced.jar --help

java -jar ./target/application-runner-advanced.jar --version

java -jar ./target/application-runner-advanced.jar --print
```

Utilizzo con spring-boot:run:

```shell
# Stampa help
mvn clean spring-boot:run "-Dspring-boot.run.arguments=--help"

# Stampa versione
mvn clean spring-boot:run "-Dspring-boot.run.arguments=--version"

# Esecuzione: stampa variabili passate
mvn clean spring-boot:run "-Dspring-boot.run.arguments=--print"
```