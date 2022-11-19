# application-runner-advanced

Batch avanzato basato su ApplicationRunner:

Utilizzo con java -jar:
```shell
mvn clean package

# Stampa help
java -jar ./target/application-runner-advanced.jar --help

# Stampa versione
java -jar ./target/application-runner-advanced.jar --version

# Esecuzione: stampa variabili passate
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