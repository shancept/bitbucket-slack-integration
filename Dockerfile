FROM maven:3.8.7-openjdk-18-slim AS build
COPY pom.xml /usr/src/app/
COPY src /usr/src/app/src

WORKDIR /usr/src/app
RUN mvn clean package -DskipTests

FROM openjdk:18-jdk-slim

COPY --from=build /usr/src/app/target/*.jar /usr/app/app.jar

ENTRYPOINT ["java","-jar","/usr/app/app.jar"]