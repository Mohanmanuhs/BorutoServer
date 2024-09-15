# Stage 1: Build
FROM openjdk:21 AS build

# Install xargs and other basic utilities
RUN apt-get update && apt-get install -y findutils

# Copy source code to the container
COPY . /home/gradle/src
WORKDIR /home/gradle/src

# Give execute permissions to the gradlew script
RUN chmod +x ./gradlew

# Use the Gradle wrapper to build the fat JAR
RUN ./gradlew --no-daemon buildFatJar

# Stage 2: Run
FROM openjdk:21

# Expose the port the application will run on
EXPOSE 8081

# Create an app directory and copy the fat JAR from the build stage
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/DemoServer-all.jar

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/DemoServer-all.jar"]
