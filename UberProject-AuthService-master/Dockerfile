FROM gradle:8.14-jdk17 AS build
WORKDIR /app

# Copy entire repository context
COPY . .

# Run publishToMavenLocal and bootJar in ONE step so ~/.m2 is preserved
RUN cd /app/UberProject-EntityService && gradle publishToMavenLocal --no-daemon \
    && cd /app/UberProject-AuthService-master && gradle bootJar --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/UberProject-AuthService-master/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]