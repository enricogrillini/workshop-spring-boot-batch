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
java -jar ./target/application-runner-advanced.jar --endpoint=http://localhost:8082
```

Utilizzo con spring-boot:run:

```shell
# Stampa help
mvn clean spring-boot:run "-Dspring-boot.run.arguments=--help"

# Stampa versione
mvn clean spring-boot:run "-Dspring-boot.run.arguments=--version"

# Esecuzione: stampa variabili passate
mvn clean spring-boot:run "-Dspring-boot.run.arguments=--endpoint=http://localhost:8082"
```

Docker Windows
```powershell
docker run `
   --mount type=bind,source="$(pwd)/target,target=/app" `
   openjdk:17.0.2-jdk bash -c "cp /app/application-runner-advanced.jar /opt/batch.war && java -jar /opt/batch.war --version"
```

Docker Linux
```shell
docker run \
   --mount type=bind,source="$(pwd)/target,target=/app" \
   openjdk:17.0.2-jdk java -jar /app/application-runner-advanced.jar --version
```
