FROM gradle:8.14-jdk17 AS build
WORKDIR /app

COPY . .

RUN cd /app/UberProject-EntityService && gradle publishToMavenLocal --no-daemon \
    && cd /app/UberSocketServer && gradle bootJar --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/UberSocketServer/build/libs/*.jar app.jar
EXPOSE 7779
ENTRYPOINT ["java", "-jar", "app.jar"]