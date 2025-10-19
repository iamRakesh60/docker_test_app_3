# 1. Use an official OpenJDK runtime as a base image
FROM openjdk:17-jdk-slim

# 2. Set the working directory inside the container
WORKDIR /app

# 3. Copy the JAR file from target folder into the container
COPY target/test.jar /app/test.jar

# 4. Expose the port the app runs on
EXPOSE 9090

# 5. Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "test.jar"]
