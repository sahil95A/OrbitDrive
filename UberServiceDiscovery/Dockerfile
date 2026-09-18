FROM gradle:8.14-jdk17 AS build
WORKDIR /app
COPY . .
WORKDIR /app/UberServiceDiscovery
RUN gradle bootJar --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/UberServiceDiscovery/build/libs/*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]