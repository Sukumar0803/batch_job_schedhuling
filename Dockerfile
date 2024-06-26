FROM openjdk:21-jdk-slim

LABEL authors="sukumar"

COPY target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]