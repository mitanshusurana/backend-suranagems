# Use the official OpenJDK 23 image for the build stage
FROM openjdk:23-jdk AS build

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and build files
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle build.gradle
COPY settings.gradle settings.gradle

# Copy the source code
COPY src src

# Make the Gradle wrapper executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew bootJar --no-daemon

# Use a slim image for the final stage
FROM openjdk:23-jdk-slim

# Expose the application port
EXPOSE 8080

# Copy the built JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "app.jar"]
