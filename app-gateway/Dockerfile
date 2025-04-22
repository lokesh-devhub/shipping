# Use OpenJDK as base image
FROM openjdk:19-jdk

# Add a label (optional)
LABEL maintainer="your-email@example.com"

# Add a volume to store logs (optional)
VOLUME /tmp

# Set the working directory inside the container
WORKDIR /app

COPY build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8083

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]