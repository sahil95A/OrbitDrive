FROM gradle:8.14-jdk17 AS build
WORKDIR /app

# Copy full context so subfolders are accessible
COPY . .

# Step A: Build and publish EntityService to local Maven cache
WORKDIR /app/UberProject-EntityService
RUN gradle publishToMavenLocal --rerun-tasks --no-daemon

# Step B: Build UberSocketServer
WORKDIR /app/UberSocketServer
RUN gradle bootJar --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/UberSocketServer/build/libs/*.jar app.jar
EXPOSE 7779
ENTRYPOINT ["java", "-jar", "app.jar"]