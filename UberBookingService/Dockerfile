FROM gradle:8.14-jdk17 AS build
WORKDIR /app

# Copy EntityService source and publish it to local Maven
COPY UberProject-EntityService /app/UberProject-EntityService
RUN cd /app/UberProject-EntityService && gradle publishToMavenLocal --no-daemon

# Copy BookingService source and build
COPY UberBookingService /app/UberBookingService
WORKDIR /app/UberBookingService
RUN gradle bootJar --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/UberBookingService/build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]