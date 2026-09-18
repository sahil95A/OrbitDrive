FROM gradle:8.14-jdk17 AS build
WORKDIR /app

# Copy EntityService source first and publish to local maven
COPY UberProject-EntityService /app/UberProject-EntityService
RUN cd /app/UberProject-EntityService && gradle publishToMavenLocal --no-daemon

# Now copy AuthService source and build
COPY UberProject-AuthService-master /app/UberProject-AuthService-master
WORKDIR /app/UberProject-AuthService-master
RUN gradle bootJar --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/UberProject-AuthService-master/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]