FROM maven:3.8.6-openjdk-18-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -P dev

FROM openjdk:17-alpine
ARG JAR_NAME="index-storage-0.0.1-SNAPSHOT.jar"
ARG HTTP_PORT=8080

WORKDIR /usr/src/app
EXPOSE ${HTTP_PORT}
COPY --from=build /home/app/target/${JAR_NAME} ./app.jar
CMD ["java","-jar", "./app.jar"]
