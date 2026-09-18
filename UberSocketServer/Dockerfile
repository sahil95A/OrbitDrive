FROM gradle:8.14-jdk17 AS build
WORKDIR /app
COPY . .
WORKDIR /app/UberSocketServer
RUN gradle bootJar --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/UberSocketServer/build/libs/*.jar app.jar
EXPOSE 7779
ENTRYPOINT ["java", "-jar", "app.jar"]