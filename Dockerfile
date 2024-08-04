FROM openjdk:21-jdk-slim AS base
COPY peacock-api/build/libs/peacock-api.jar peacock.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "./peacock.jar"]
