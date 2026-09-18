FROM gradle:8.14-jdk17 AS build
WORKDIR /app
COPY . .

WORKDIR /app/UberProject-EntityService
RUN gradle publishToMavenLocal --no-daemon

WORKDIR /app/UberProject-AuthService-master
RUN gradle bootJar --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/UberProject-AuthService-master/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]