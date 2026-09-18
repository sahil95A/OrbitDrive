FROM gradle:8.14-jdk17 AS build
WORKDIR /app
COPY . .
WORKDIR /app/UberProject-LocationService
RUN gradle bootJar --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/UberProject-LocationService/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]