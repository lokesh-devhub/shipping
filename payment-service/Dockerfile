# Use OpenJDK as base image
FROM openjdk:19-jdk

# Add a label (optional)
LABEL maintainer="your-email@example.com"

# Add a volume to store logs (optional)
VOLUME /tmp

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar to container
COPY build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8081

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]