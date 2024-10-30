# Stage 1: Build the application
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Set the working directory
WORKDIR /app

# Copy the Maven configuration files
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Setup the environment for the application
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the first stage
COPY --from=build /app/target/todo-app-0.0.1-SNAPSHOT.jar todo-app.jar

# Expose the application port
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "todo-app.jar"]
