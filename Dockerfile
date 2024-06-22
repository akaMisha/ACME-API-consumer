# Use a base image with Java runtime
FROM openjdk:17

# Set the working directory
WORKDIR /app

# Copy the application jar file to the container
COPY target/acme-api-consumer-0.0.1-SNAPSHOT.jar /app/acme-api-consumer-0.0.1-SNAPSHOT.jar

# Expose the port on which the app runs
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "acme-api-consumer-0.0.1-SNAPSHOT.jar"]