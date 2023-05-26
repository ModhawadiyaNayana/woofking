FROM maven:3.8.2-openjdk-17 AS build
COPY ./target/notification-0.0.1.jar notification-0.0.1.jar
ENTRYPOINT ["java","-jar","/notification-0.0.1.jar"]
EXPOSE 8081