# Deploy Spring Boot Application on Docker with Maven
FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
RUN chmod +x mvnw
RUN ./mvnw package -DskipTests
FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build target/*.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=production","-jar","/app.jar"]