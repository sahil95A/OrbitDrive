FROM gradle:8.14-jdk17 AS build
WORKDIR /app

COPY UberProject-EntityService /app/UberProject-EntityService
WORKDIR /app/UberProject-EntityService
RUN gradle publishToMavenLocal --rerun-tasks --no-daemon

WORKDIR /app
COPY UberBookingService /app/UberBookingService
WORKDIR /app/UberBookingService
RUN gradle bootJar --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/UberBookingService/build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]