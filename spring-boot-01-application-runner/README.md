# Spring-boot-rest-api

Batch base basato su ApplicationRunner

```shell
mvn clean package

java -jar ./target/application-runner-1.0.0-SNAPSHOT.jar --first-argument=first-value --second-argument=second-value third-argument

mvn clean spring-boot:run "-Dspring-boot.run.arguments=--first-argument=first-value --second-argument=second-value third-argument"
```