FROM maven:3.8.2-openjdk-17 AS build
COPY pom.xml .
COPY src ./src
RUN mvn clean install
ENTRYPOINT ["java","-jar","./target/notification-0.0.1.jar"]
EXPOSE 8081