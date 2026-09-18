FROM gradle:8.14-jdk17 AS build
WORKDIR /app
COPY . .
# Navigate into the subfolder where build.gradle actually lives
WORKDIR /app/UberProject-EntityService
RUN gradle publishToMavenLocal --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/UberProject-EntityService/build/libs/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]