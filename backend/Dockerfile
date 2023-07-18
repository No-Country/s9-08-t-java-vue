# Base docker image
FROM openjdk:17

# Set the working directory
WORKDIR /app

# Copy the application JAR file
COPY target/movenow-0.0.1-SNAPSHOT.jar .

# Expose the application port
EXPOSE 8080

# Define the entrypoint command
ENTRYPOINT ["java", "-jar", "movenow-0.0.1-SNAPSHOT.jar"]