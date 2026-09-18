# Stage 1: Build the JAR using Gradle
FROM gradle:8.14-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]